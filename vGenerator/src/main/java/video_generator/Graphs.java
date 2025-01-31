
package video_generator;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import guru.nidi.graphviz.attribute.Records;
import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.attribute.Records.turn;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import guru.nidi.graphviz.model.Graph;
import static guru.nidi.graphviz.model.Link.to;
import guru.nidi.graphviz.model.Node;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Graphs {
    
    private final Map<String, String> _colors;
    private final String _graph_representation;
    private final int _width;
    private final int _height;
    private int _status;
    
    public Graphs(String graph_representation, int width, int height) {
        this._graph_representation = graph_representation;
        this._width = width;
        this._height = height;
        this._status = 1;
        
        this._colors = new HashMap<>();
        this._colors.put("rojo","red");
        this._colors.put("roja","red");
        this._colors.put("naranjo","orange");
        this._colors.put("naranja","orange");
        this._colors.put("amarillo","yellow");
        this._colors.put("amarilla","yellow");
        this._colors.put("verde","green");
        this._colors.put("azul","blue");
        this._colors.put("índigo ","indigo");
        this._colors.put("indigo ","indigo");
        this._colors.put("cian","cyan");
        this._colors.put("violeta","violet");
    }
    
    public BufferedImage getNodeRepresentation(ArrayList<String> Nodes) {
        Scanner first_word_scan = new Scanner(this._graph_representation).useDelimiter(" ");
        String graph_representation = first_word_scan.next();
        switch(graph_representation){
            case "graph":
                first_word_scan.close();
                return nodeGraph(Nodes);
            case "three":
                first_word_scan.close();
                return node3Graph(Nodes);
            case "sliding":
                first_word_scan.close();
                return node8Graph(Nodes);
            case "matrix":
                int rows = first_word_scan.nextInt();
                int columns = first_word_scan.nextInt();
                first_word_scan.close();
                return matrixGraph(rows, columns, Nodes);
            default:
                first_word_scan.close();
                return nodeGraph(Nodes);
        }
    }

    private BufferedImage nodeGraph(ArrayList<String> Nodes) {
        
        if(!Nodes.isEmpty() && Nodes.size() % 2 == 0)
            Nodes.remove(Nodes.size()-1);
        
        if(Nodes.size() != 2)
            Collections.reverse(Nodes);
        
        Attributes font = Font.name("Arial");
        Graph g = graph("nodeGraph").directed()
            .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
            .nodeAttr().with(font)
            .linkAttr().with("class", "link-class");

        Node previous_node = null;
        Label previous_label = null;
        Iterator<String> iterator = Nodes.iterator();
        while(iterator.hasNext()){
            Node next_node = node(iterator.next());
            Label next_label = (iterator.hasNext()) ? Label.html("<table border='0' bgcolor='#E7F0FF' align='left' width='50%'><tr><td align='left'>" + iterator.next() + "</td></tr></table>")/*.locate(Label.Location.CENTER).justify(Label.Justification.LEFT)*/ : Label.of("");
            if(previous_node != null)
                g = g.with(previous_node.with(Color.rgb(0,64,176)).with().link(to(next_node).with(Style.SOLID, previous_label, Color.rgb(0,64,176))),next_node.with(Color.rgb(0,64,176)));
            previous_node = next_node;
            previous_label = next_label;
            if(Nodes.size() < 3)
                g = g.with(previous_node.with(Color.rgb(0,64,176)));
        }
        
        return Graphviz.fromGraph(g).width(this._width).height(this._height).render(Format.PNG).toImage();
    }
    
    private BufferedImage node3Graph(ArrayList<String> Nodes) {
        
        if(!Nodes.isEmpty() && Nodes.size() % 2 == 0)
            Nodes.remove(Nodes.size()-1);
        
        if(Nodes.size() != 2)
            Collections.reverse(Nodes);
        
        Attributes font = Font.name("Arial");
        Graph g = graph("nodeGraph").directed()
            .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
            .nodeAttr().with(font)
            .linkAttr().with("class", "link-class");

        Node previous_node = null;
        Label previous_label = null;
        Iterator<String> iterator = Nodes.iterator();
        while(iterator.hasNext()){
            String node3 = iterator.next();
            String[] nine_chars = node3.split("[, ]+");
            if(nine_chars.length<3) {
                this._status = 0;
                return new BufferedImage(this._width, this._height, BufferedImage.TYPE_INT_ARGB);
            }
            Label next_node_label = Label.html(
                    "<TABLE border='0' cellborder='1' cellspacing='0' cellpadding='0' color='black'>" +
                    "   <TR>" +
                    "       <TD WIDTH='50' BGCOLOR='" + this.color(nine_chars[0]) + "'> </TD>" +
                    "       <TD WIDTH='50' BGCOLOR='" + this.color(nine_chars[1]) + "'> </TD>" +
                    "       <TD WIDTH='50' BGCOLOR='" + this.color(nine_chars[2]) + "'> </TD>" +
                    "   </TR>" +
                    "</TABLE>"
                    );
            Node next_node = node(node3).with(next_node_label, Shape.PLAIN_TEXT);
            
            Label next_label = (iterator.hasNext()) ? Label.html("<table border='0' bgcolor='#E7F0FF' align='left' width='50%'><tr><td align='left'>" + iterator.next() + "</td></tr></table>")/*.locate(Label.Location.CENTER).justify(Label.Justification.LEFT)*/ : Label.of("");
            if(previous_node != null)
                g = g.with(previous_node.with(Color.rgb(0,64,176)).with().link(to(next_node).with(Style.SOLID, previous_label, Color.rgb(0,64,176))),next_node.with(Color.rgb(0,64,176)));
            previous_node = next_node;
            previous_label = next_label;
            if(Nodes.size() < 3)
                g = g.with(previous_node.with(Color.rgb(0,64,176)));
        }
        
        return Graphviz.fromGraph(g).width(this._width).height(this._height).render(Format.PNG).toImage();
    }
    
    private BufferedImage node8Graph(ArrayList<String> Nodes) {
        
        if(!Nodes.isEmpty() && Nodes.size() % 2 == 0)
            Nodes.remove(Nodes.size()-1);
        
        if(Nodes.size() != 2)
            Collections.reverse(Nodes);
        
        Attributes font = Font.name("Arial");
        Graph g = graph("nodeGraph").directed()
            .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
            .nodeAttr().with(font)
            .linkAttr().with("class", "link-class");

        Node previous_node = null;
        Label previous_label = null;
        Iterator<String> iterator = Nodes.iterator();
        while(iterator.hasNext()){
            String node8 = iterator.next().toUpperCase();
            char[] nine_chars = node8.toCharArray();
            if(nine_chars.length<9) {
                this._status = 0;
                return new BufferedImage(this._width, this._height, BufferedImage.TYPE_INT_ARGB);
            }
            Node next_node = node(node8).with(Records.of(turn(rec("f0", nine_chars[0]+""), rec("f0", nine_chars[3]+""), rec("f0", nine_chars[6]+"")),
                                                                       turn(rec("f1", nine_chars[1]+""), rec("f0", nine_chars[4]+""), rec("f0", nine_chars[7]+"")),
                                                                       turn(rec("f2", nine_chars[2]+""), rec("f0", nine_chars[5]+""), rec("f0", nine_chars[8]+""))));
            Label next_label = (iterator.hasNext()) ? Label.html("<table border='0' bgcolor='#E7F0FF' align='left' width='50%'><tr><td align='left'>" + iterator.next() + "</td></tr></table>")/*.locate(Label.Location.CENTER).justify(Label.Justification.LEFT)*/ : Label.of("");
            if(previous_node != null)
                g = g.with(previous_node.with(Color.rgb(0,64,176)).with().link(to(next_node).with(Style.SOLID, previous_label, Color.rgb(0,64,176))),next_node.with(Color.rgb(0,64,176)));
            previous_node = next_node;
            previous_label = next_label;
            if(Nodes.size() < 3)
                g = g.with(previous_node.with(Color.rgb(0,64,176)));
        }
        
        return Graphviz.fromGraph(g).width(this._width).height(this._height).render(Format.PNG).toImage();
    }
    
    private BufferedImage matrixGraph(int rows, int columns, ArrayList<String> Nodes) {
        
        if(!Nodes.isEmpty() && Nodes.size() % 2 == 0)
            Nodes.remove(Nodes.size()-1);
        
        if(Nodes.size() != 2)
            Collections.reverse(Nodes);
        
        Attributes font = Font.name("Arial");
        Graph g = graph("nodeGraph").directed()
            .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
            .nodeAttr().with(font)
            .linkAttr().with("class", "link-class");

        Node previous_node = null;
        Label previous_label = null;
        Iterator<String> iterator = Nodes.iterator();
        while(iterator.hasNext()){
            String matrix = iterator.next();
            String[] node = matrix.split("[, ]+");
            if(node.length<2) {
                this._status = 0;
                return new BufferedImage(this._width, this._height, BufferedImage.TYPE_INT_ARGB);
            }
            String label = 
                    "<TABLE border='0' cellborder='1' cellspacing='0' cellpadding='0' color='black'>";
            for(int m = 0; m < rows; ++m) {
                label = label +
                    "   <TR>";
                for(int n = 0; n < columns; ++n)
                    try {
                        label = label +
                        "       <TD WIDTH='50' BGCOLOR='"+((Integer.parseInt(node[0]) == n && Integer.parseInt(node[1]) == m) ? "red" : "white")+"'> </TD>"
                        ;
                    } catch (final NumberFormatException e) {
                        this._status = 0;
                        return new BufferedImage(this._width, this._height, BufferedImage.TYPE_INT_ARGB);
                    }
                label = label +
                    "   </TR>";
            }
            label = label +
                    "</TABLE>";
            Label next_node_label = Label.html(label);
            Node next_node = node(matrix).with(next_node_label, Shape.PLAIN_TEXT);
            
            //Label next_label = (iterator.hasNext()) ? Label.of(iterator.next()) : Label.of("");
            Label next_label = (iterator.hasNext()) ? Label.html("<table border='0' bgcolor='#E7F0FF' align='left' width='50%'><tr><td align='left'>" + iterator.next() + "</td></tr></table>")/*.locate(Label.Location.CENTER).justify(Label.Justification.LEFT)*/ : Label.of("");
            if(previous_node != null)
                g = g.with(previous_node.with(Color.rgb(0,64,176)).with().link(to(next_node).with(Style.SOLID, previous_label, Color.rgb(0,64,176))),next_node.with(Color.rgb(0,64,176)));
            previous_node = next_node;
            previous_label = next_label;
            if(Nodes.size() < 3)
                g = g.with(previous_node.with(Color.rgb(0,64,176)));
        }
        
        return Graphviz.fromGraph(g).width(this._width).height(this._height).render(Format.PNG).toImage();
    }
    
    public ArrayList<BufferedImage> nodeSequenceGraph(ArrayList<String> Nodes) {
        ArrayList<BufferedImage> sequence = new ArrayList<>();
        
        Graph g = graph("nodeGraph").directed()
            .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
            .nodeAttr().with(Font.name("arial"))
            .linkAttr().with("class", "link-class");

        String previous_node = new String();
        for(String node_name : Nodes) {
            if(!previous_node.isEmpty())
                g = g.with(node(previous_node).with(Color.BLACK).link(node(node_name)));
            previous_node = node_name;
            sequence.add(Graphviz.fromGraph(g).width(this._width).height(this._height).render(Format.PNG).toImage());
        }
        return sequence;
    }
    
    private String color(String color) {
        color = color.toLowerCase();
        String _color = this._colors.get(color);
        return (_color != null) ? _color : color;
    }
    
    public int status() {
        return this._status;
    }
}
