/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package video_generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Frames {
    
    //private final String pathname;
    private int frame_count;
    private final String output_file_format;
    
    private final Font title_font;
    private final Font subtitle_font;
    private final Font body_font;
    private final int padding;
    private final int border_size;
    
    private final Rectangle background;
    private final Rectangle border;
    private final Rectangle header;
    private final Rectangle footer;
    
    private final Color background_color;
    private final Color border_color;
    private final Color header_color;
    private final Color footer_color;
    
    private final Color text_color;
    private final Color title_color;
    private final Color UBB_color;
    
    private BufferedImage logo;
    private final String footer_image_pathname;
    private final Point logo_coordinates;
    
    private final ArrayList<BufferedImage> frames;
    
    private final ArrayList<ArrayList<String>> states;
    private int previous_y;
    private int final_frame;
    
    private int _status;
    
    // parameters
    private final String graph_representation;
    private final boolean avatar;
    private final boolean english = true;

    public Frames(String graph_representation, boolean avatar) {
        this.frame_count = 0;
        this.output_file_format = "png";
        
        this.title_font = new Font("Georgia", Font.BOLD, 78);
        this.subtitle_font = new Font("Georgia", Font.BOLD, 55);
        this.body_font = new Font("Georgia", Font.BOLD, 38);
        this.padding = 20;
        this.border_size = 1;
        
        this.background = new Rectangle(0, 0, 2000, 1500);
        
        this.header = new Rectangle(this.background.x, this.background.y, this.background.width, this.background.y + this.title_font.getSize() + this.padding);
        
        this.border = new Rectangle(this.header.x, this.header.height, this.header.width - this.border_size, this.background.height - (this.header.height + this.body_font.getSize() + this.border_size));
        
        this.footer = new Rectangle(this.background.width - (this.body_font.getSize() * 2), this.background.height - this.body_font.getSize(), this.background.width, this.body_font.getSize());
        
        this.background_color = Color.white;
        this.border_color = new Color(0,64,176);
        this.header_color = new Color(0,64,176);
        this.footer_color = new Color(0,64,176);
        
        this.text_color = new Color(53,53,53);
        this.title_color = Color.white;
        this.UBB_color =  new Color(0,64,176);
        
        this.footer_image_pathname = "images/ubb_logo.png"; 
        try {
            this.logo = ImageIO.read(new File(this.footer_image_pathname));
        } catch (IOException ex) {
            Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.logo_coordinates = new Point(background.width - (this.logo.getWidth() + this.border_size),background.height - (this.logo.getHeight() + this.footer.height + this.border_size));
        
        frames = new ArrayList();
        
        this.states = new ArrayList<>();
        this.previous_y = 0;
        final_frame = -1;
        
        this.graph_representation = graph_representation;
        this.avatar = avatar;
        
        this._status = 1;
    }
    
    public void printTitleFrame(ArrayList<String> lines) {
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = 200;
        graphics.setColor(this.text_color);
        graphics.setFont(this.title_font);
        graphics.drawString(lines.remove(0),x,y);
        y += this.title_font.getSize() + this.padding;
        graphics.drawString(lines.remove(0),x,y);
        
        graphics.setColor(this.UBB_color);
        graphics.setFont(this.subtitle_font);
        for(String line : lines){
            y += this.title_font.getSize() + this.padding;
            graphics.drawString(line,x,y);
        }

        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);
        
        // duplicate
        if(avatar)
            this.frames.add(duplicateImage(image));
    }
    
    public void printContentsFrame(ArrayList<String> lines) {
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        ArrayList<String> _lines = new ArrayList(
                (english) ?
                List.of(
                "Contents",
                ""
                )
                :
                List.of(
                "Contenidos",
                ""
                )
        );
        
        _lines.addAll(lines);
        lines.clear();
        
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = this.title_font.getSize();
        graphics.setColor(this.title_color);
        graphics.setFont(this.title_font);
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setColor(this.text_color);
        graphics.setFont(this.subtitle_font);
        y += this.title_font.getSize();
        graphics.drawString(_lines.remove(0),x,y);
        
        for(String line : _lines){
            y += this.title_font.getSize() + this.padding;
            graphics.drawString(line,x,y);
        }

        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);
        
        // duplicate
        if(avatar)
            this.frames.add(duplicateImage(image));
    }
    
    public void printDataStructuresFrame(ArrayList<String> lines) {
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        ArrayList<String> _lines = new ArrayList(
                (english) ?
                List.of(
                "1. Definitions",
                "Initially the following data structures are defined:"
                )
                :
                List.of(
                "1. Definición de estructuras de datos",
                "Inicialmente se definen las siguientes estructuras de datos:"
                )
        );
        
        _lines.addAll(lines);
        lines.clear();
        
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = this.title_font.getSize();
        graphics.setColor(this.title_color);
        graphics.setFont(this.title_font);
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setColor(this.text_color);
        graphics.setFont(this.subtitle_font);
        y += this.title_font.getSize() + this.padding;
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setFont(this.body_font);
        y += this.padding;
        
        for(String line : _lines){
            y += this.title_font.getSize();
            graphics.drawString(line,x,y);
        }
        
        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);
        
        // duplicate
        if(avatar)
            this.frames.add(duplicateImage(image));
    }
    
    public void printVariableDeclarationFrame(ArrayList<String> lines, ArrayList<ArrayList<String>> graph_data){
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        ArrayList<String> _lines = new ArrayList(
                (english) ?
                List.of(
                "2. Declarations",
                "Subsequently, the following variables are declared:"
                )
                :
                List.of(
                "2. Declaración de variables",
                "Posteriormente se declaran las siguientes variables:"
                )
        );
        
        _lines.addAll(lines);
        lines.clear();
                
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = this.title_font.getSize();
        graphics.setColor(this.title_color);
        graphics.setFont(this.title_font);
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setColor(this.text_color);
        graphics.setFont(this.subtitle_font);
        y += this.title_font.getSize() + this.padding;
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setFont(this.body_font);
        y += this.padding;
        
        for(String line : _lines){
            y += this.title_font.getSize();
            graphics.drawString(line,x,y);
        }
        
        // graph
        this.previous_y = y;
        int height = 500;
        Rectangle graph_box = new Rectangle(this.background.x, y + this.padding, this.background.width, height + (this.title_font.getSize()/2) + 2);
        graphics.setColor(this.UBB_color);
        graphics.draw(graph_box);
        
        int n_graphs = graph_data.size();
        if(n_graphs < 1)
            return;
        if(n_graphs > 4)
            n_graphs = 4;
        int x_increment = ( this.background.width - 2 ) / n_graphs;
        if(x_increment > height)
            x_increment = height;
        if(x_increment == height) {
            x = 750;
        } else
            x = (background.width/3)-(x_increment/2);
        y = y + this.padding + 1;
        
        int i = 0;
        boolean second_row = false;
        for(ArrayList<String> arr : graph_data) {
            this.states.add((ArrayList<String>) arr.clone());
            if(i > 3 && !second_row){
                y = y + height + 1;
                x = 1;
                second_row = true;
                
                graph_box = new Rectangle(this.background.x, y - 1, this.background.width, height + 2);
                graphics.setColor(this.UBB_color);
                graphics.draw(graph_box);
            }
            Graphs graph = new Graphs(graph_representation,x_increment, height);
            graphics.drawImage(graph.getNodeRepresentation(arr), x, y,null);
            if(graph.status() == 0)
                this._status = 0;
            if(i==0)
            {
                if(english)
                    graphics.drawString("initial STATE",(x+x_increment/4),(y + height + 5));
                else
                    graphics.drawString("ESTADO inicial",(x+x_increment/6),(y + height + 5));
            }
            else
            {
                if(english)
                    graphics.drawString("target STATE",(x+x_increment/4),(y + height + 5));
                else
                    graphics.drawString("ESTADO objetivo",(x+x_increment/6),(y + height + 5));
            }
            x += background.width/3;
            ++i;
            if(i==2)
                break;
        }
        graph_data.clear();
        
        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);
        
        // duplicate
        if(avatar)
            this.frames.add(duplicateImage(image));
    }
    
    public void printQueueFrame(ArrayList<String> lines, ArrayList<ArrayList<String>> graph_data){
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        ArrayList<String> _lines = new ArrayList(
                (english) ? List.of("3. Search") : List.of("3. Proceso de búsqueda")
        );
        _lines.addAll(lines);
        lines.clear();
        
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = this.title_font.getSize();
        graphics.setColor(this.title_color);
        graphics.setFont(this.title_font);
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setColor(this.text_color);
        graphics.setFont(this.subtitle_font);
        y += this.title_font.getSize() + this.padding;
        graphics.drawString(_lines.remove(0),x,y);
        if(_lines.size() > 1) {
            y += this.subtitle_font.getSize() + this.padding;
            graphics.drawString(_lines.remove(0),x,y);
        }
        graphics.setColor(this.UBB_color);
        graphics.setFont(this.body_font);
        y += this.padding;
        
        for(String line : _lines){
            y += this.title_font.getSize();
            graphics.drawString(line,x,y);
        }
        
        // graph
        int height = 500;
        Rectangle graph_box = new Rectangle(this.background.x, y + this.padding, this.background.width, height + 2);
        graphics.setColor(this.UBB_color);
        graphics.draw(graph_box);
        
        int n_graphs = graph_data.size();
        if(n_graphs < 1)
            return;
        if(n_graphs > 4)
            n_graphs = 4;
        int x_increment = ( this.background.width - 2 ) / n_graphs;
        if(x_increment > height)
            x_increment = height;
        if(x_increment == height) {
            if(n_graphs == 3)
                x = 250;
            else
                if(n_graphs == 2)
                    x = 500;
                else
                    x = 750;
        } else
            x = 1;
        y = y + this.padding + 1;
        
        int i = 0;
        boolean second_row = false;
        for(ArrayList<String> arr : graph_data) {
            if(i > 3 && !second_row){
                y = y + height + 1;
                x = 1;
                second_row = true;
                
                graph_box = new Rectangle(this.background.x, y - 1, this.background.width, height + 2);
                graphics.setColor(this.UBB_color);
                graphics.draw(graph_box);
            }
            Graphs graph = new Graphs(graph_representation,x_increment, height);
            graphics.drawImage(graph.getNodeRepresentation(arr), x, y,null);
            if(graph.status() == 0)
                this._status = 0;
            x += x_increment;
            ++i;
        }
        graph_data.clear();
        
        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);
    }
    
    public void printStatusFrame(ArrayList<String> lines, ArrayList<String> graph_data){
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        ArrayList<String> _lines = new ArrayList(lines);
        
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = this.title_font.getSize();
        graphics.setColor(this.title_color);
        graphics.setFont(this.title_font);
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setColor(this.text_color);
        graphics.setFont(this.subtitle_font);
        y += this.title_font.getSize();
        graphics.drawString(_lines.remove(0),x,y);
        y += this.subtitle_font.getSize() + this.padding;
        String status_line = _lines.remove(0);
        graphics.drawString(status_line,x,y);
        graphics.setColor(this.UBB_color);
        graphics.setFont(this.body_font);
        y += this.padding;
        
        // graph
        int height = 500;
        Rectangle graph_box = new Rectangle(this.background.x, y + this.padding, this.background.width, height + 2);
        graphics.setColor(this.UBB_color);
        graphics.draw(graph_box);
        
        int n_graphs = 1;
        if(n_graphs < 1)
            return;
        if(n_graphs > 4)
            n_graphs = 4;
        int x_increment = ( this.background.width - 2 ) / n_graphs;
        if(x_increment > height)
            x_increment = height;
        if(x_increment == height) {
            if(n_graphs == 3)
                x = 250;
            else
                if(n_graphs == 2)
                    x = 500;
                else
                    x = 750;
        } else
            x = 1;
        y = y + this.padding + 1;
        
        int i = 0;
        boolean second_row = false;
        ArrayList<String> arr = graph_data;
        if(i > 3 && !second_row){
            y = y + height + 1;
            x = 1;
            second_row = true;

            graph_box = new Rectangle(this.background.x, y - 1, this.background.width, height + 2);
            graphics.setColor(this.UBB_color);
            graphics.draw(graph_box);
        }
        Graphs graph = new Graphs(graph_representation,x_increment, height);
        graphics.drawImage(graph.getNodeRepresentation(arr), x, y,null);
        if(graph.status() == 0)
                this._status = 0;
        x += x_increment;
        ++i;
        
        graph_data.clear();
        
        x = 30;
        y = y + height + this.padding + 1;
        for(String line : _lines){
            y += this.title_font.getSize();
            graphics.drawString(line,x,y);
        }
        
        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);

        // finished
        if(lines.get(0).charAt(0) == '4') {

            // duplicate
            if(this.avatar)
                this.frames.add(duplicateImage(image));

            this.final_frame =  this.frame_count;
        }
        lines.clear();
    }
    
    public void printMethodFrame(ArrayList<String> lines){
        RenderingHints rendering_hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rendering_hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        ArrayList<String> _lines = new ArrayList(lines);
        lines.clear();
        
        // frame
        BufferedImage image = new BufferedImage(background.width, background.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(this.background_color);
        graphics.fill(this.background);
        graphics.setRenderingHints(rendering_hints);
        graphics.setColor(this.header_color);
        graphics.fill(this.header);
        graphics.setColor(this.border_color);
        graphics.draw(this.border);
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        
        // logo
        graphics.drawImage(logo,this.logo_coordinates.x,this.logo_coordinates.y,null);
        
        // text
        int x = 30;
        int y = this.title_font.getSize();
        graphics.setColor(this.title_color);
        graphics.setFont(this.title_font);
        String stage_line = _lines.remove(0);
        graphics.drawString(stage_line,x,y);
        graphics.setColor(this.text_color);
        graphics.setFont(this.subtitle_font);
        y += this.title_font.getSize() + this.padding;
        graphics.drawString(_lines.remove(0),x,y);
        graphics.setFont(this.body_font);
        y += this.padding;
        
        for(String line : _lines){
            y += this.title_font.getSize();
            graphics.drawString(line,x,y);
        }
        
        // graph
        y = this.previous_y;
        ArrayList<ArrayList<String>> graph_data = this.states;
        int height = 500;
        Rectangle graph_box = new Rectangle(this.background.x, y + this.padding, this.background.width, height + (this.title_font.getSize()/2) + 2);
        graphics.setColor(this.UBB_color);
        graphics.draw(graph_box);
        
        int n_graphs = graph_data.size();
        if(n_graphs < 1)
            return;
        if(n_graphs > 4)
            n_graphs = 4;
        int x_increment = ( this.background.width - 2 ) / n_graphs;
        if(x_increment > height)
            x_increment = height;
        x = (background.width/3)-(x_increment/2);
        y = y + this.padding + 1;
        
        int i = 0;
        for(ArrayList<String> arr : graph_data) {
            Graphs graph = new Graphs(graph_representation,x_increment, height);
            graphics.drawImage(graph.getNodeRepresentation(arr), x, y,null);
            if(graph.status() == 0)
                this._status = 0;
            if(i==0)
            {
                if(english)
                    graphics.drawString("initial STATE",(x+x_increment/4),(y + height + 5));
                else
                    graphics.drawString("ESTADO inicial",(x+x_increment/6),(y + height + 5));
            }
            else
            {
                if(english)
                    graphics.drawString("target STATE",(x+x_increment/4),(y + height + 5));
                else
                    graphics.drawString("ESTADO objetivo",(x+x_increment/6),(y + height + 5));
            }
            x += background.width/3;
            ++i;
            if(i==2)
                break;
        }
        graph_data.clear();
        
        // frame number
        ++this.frame_count;
        
        // page
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        
        // store
        this.frames.add(image);
        if(stage_line.charAt(0) == '3') {
            // duplicate
            if(this.avatar)
                this.frames.add(duplicateImage(image));
        }
    }
    
    private BufferedImage duplicateImage(BufferedImage image) {
        ++this.frame_count;
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        BufferedImage _image = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        Graphics2D graphics = _image.createGraphics();
        graphics.setColor(this.footer_color);
        graphics.fill(this.footer);
        graphics.setColor(this.title_color);
        graphics.setFont(this.body_font);
        graphics.drawString(String.valueOf(this.frame_count),this.footer.x + 20,this.background.height - 10);
        return _image;
    }
    
    public int getFrameCount() {
        return this.frame_count;
    }
    
    public ArrayList<BufferedImage> getFrames(String directory_path, String file_name) {
        if(final_frame < 0)
        {
            System.out.printf("FINAL_FRAME: "+final_frame);
            return this.frames;
        } 
        else{
            ArrayList<BufferedImage> frames = new ArrayList<>();
            Scanner scanner = new Scanner(file_name).useDelimiter("\\.");
            File directory = new File(directory_path + File.separator + scanner.next());
            boolean directoryCreated = (directory.exists()) ? true : directory.mkdirs();
            for(int i = 0; i < final_frame; ++i) {
                frames.add(this.frames.get(i));
                if(directoryCreated)
                    try {
                        ImageIO.write(this.frames.get(i), this.output_file_format, new File(directory + File.separator + "frame_" + (i+1) + "." + this.output_file_format));
                    } catch (IOException ex) {
                        System.out.printf("FRAME NOT WRITTEN: frame_"+(i+1));
                        Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                    }
                else
                    System.out.printf("DIRECTORY NOT CREATED");
            }
            return frames;
        }
    }
    
    public int status() {
        return this._status;
    }
}
