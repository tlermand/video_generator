package windows;


import analyzer.EvalVisitor;
import analyzer.MuLexer;
import analyzer.MuParser;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;
import video_generator.VideoOutput;

public class windowMenu extends JLabel {
    
    JFrame ventana;
    windowMenu v;
    BufferedImage fondo;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    protected final String[] selection1 = {"graph nodes","three colored pieces","sliding 8-puzzle","matrix"};
    protected final String[] selection2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    protected final String[] selection3 = {"1","2","3","4","5","6","7","8","9","10"};
    protected String directory_path = "";
    protected String filename = "";
    protected String representation = selection1[0];
    protected int fps = Integer.parseInt(selection2[4]);
    protected String rows = selection3[3];
    protected String columns = selection3[3];
    
    // parameters
    private final boolean english = true;
    
    public windowMenu(JFrame window) throws MalformedURLException, IOException{
        
        this.ventana = window;
        this.v = this;
        
        this.setSize(window.getSize());
        this.setPreferredSize(window.getSize());
        
        if(RIGHT_TO_LEFT){
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if(shouldFill){
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        
        JLabel label1 = new JLabel();
        BufferedImage logo = ImageIO.read(new File("images/ubb_logo.png"));
        label1.setIcon(new ImageIcon(logo));
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.CENTER;       // default
        c.insets = new Insets(2,0,0,0);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 2;                            // 2 2 columns wide
        c.gridy = 0;                                // 2 third row

        this.add(label1, c);
        
        JLabel label2 = new JLabel(" ");
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 1.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.CENTER;       // default
        c.insets = new Insets(2,0,0,0);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 2;                            // 2 2 columns wide
        c.gridy = 1;                                // 2 third row

        label2.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 24));
        
        this.add(label2, c);
        
        JLabel label3 = (english) ? new JLabel("Automatic generation of explanatory videos")
                                  : new JLabel("Generación automática de vídeos explicativos");
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 1.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.CENTER;       // default
        c.insets = new Insets(2,0,0,0);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 2;                            // 2 2 columns wide
        c.gridy = 2;                                // 2 third row

        label3.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 24));

        this.add(label3, c);
        
        JLabel label4 = (english) ? new JLabel("to support the teaching and learning of algorithms")
                                  : new JLabel("para apoyar la enseñanza y aprendizaje de algoritmos");
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 1.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.CENTER;       // default
        c.insets = new Insets(2,0,0,0);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 2;                            // 2 2 columns wide
        c.gridy = 3;                                // 2 third row

        label4.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 24));

        this.add(label4, c);
        
        JLabel label5 = new JLabel(" ");
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 1.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.CENTER;       // default
        c.insets = new Insets(2,0,0,0);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 2;                            // 2 2 columns wide
        c.gridy = 4;                                // 2 third row

        label5.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 24));
        
        this.add(label5, c);
        
        JLabel label6 = (english) ? new JLabel("Select an execution trace.")
                                  : new JLabel("Seleccionar traza de ejecución.");
        c.fill = GridBagConstraints.WEST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 1.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.WEST;         // default
        c.insets = new Insets(2,5,0,0);             // top padding
        c.gridx = 1;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 5;                                // 2 third row

        label6.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 12));
        
        this.add(label6, c);

        JButton button1 = (english) ? new JButton("Browse")
                                    : new JButton("Examinar");
        button1.setPreferredSize(new Dimension(180, 30));
        c.fill = GridBagConstraints.EAST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 1.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.EAST;         // default
        c.insets = new Insets(2,0,0,5);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 5;                                // 2 third row

        button1.setBackground(Color.white);

        button1.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT & MU Files", "txt", "mu");
            JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.dir")));
            chooser.setFileFilter(filter);
            if(chooser.showOpenDialog(ventana.getContentPane()) == JFileChooser.APPROVE_OPTION) {
                directory_path = chooser.getSelectedFile().getAbsolutePath();
                filename = chooser.getSelectedFile().getName();
                label6.setText(directory_path);
            }
        }
        });

        this.add(button1, c);
        
        JLabel label7 = (english) ? new JLabel("Select graphical representation.")
                                  : new JLabel("Seleccionar representación gráfica.");
        c.fill = GridBagConstraints.WEST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.WEST;         // default
        c.insets = new Insets(2,5,0,0);             // top padding
        c.gridx = 1;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 6;                                // 2 third row

        label7.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 12));
        
        this.add(label7, c);
        
        JLabel label81 = (english) ? new JLabel("Select matrix size.")
                                   : new JLabel("Seleccionar tamaño de la matriz.");
        label81.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 12));

        JLabel label82 = new JLabel();
        label82.setPreferredSize(new Dimension(180, 30));
        label82.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 12));
        label82.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label82.setLayout(new GridBagLayout());
        
        JComboBox combobox3 = new JComboBox(this.selection3);
        combobox3.setPreferredSize(new Dimension(90, 30));
        combobox3.setBackground(Color.white);
        combobox3.setSelectedIndex(3);
        c.fill = GridBagConstraints.WEST;
        c.ipady = 0;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.WEST;         // default
        c.insets = new Insets(0,0,0,0);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 0;
        combobox3.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            columns = (String)combobox3.getSelectedItem();
            label81.setText((english) ? ("The matrix has a size of "+rows+"x"+columns+".")
                                      : ("La matriz tiene un tamaño de "+rows+"x"+columns+"."));
        }
        });
        label82.add(combobox3,c);
        JComboBox combobox4 = new JComboBox(this.selection3);
        combobox4.setPreferredSize(new Dimension(90, 30));
        combobox4.setBackground(Color.white);
        combobox4.setSelectedIndex(3);
        c.fill = GridBagConstraints.EAST;
        c.ipady = 0;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.EAST;         // default
        c.insets = new Insets(0,0,0,0);             // top padding
        c.gridx = 1;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 0;
        combobox4.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            rows = (String)combobox4.getSelectedItem();
            label81.setText((english) ? ("The matrix has a size of "+rows+"x"+columns+".")
                                      : ("La matriz tiene un tamaño de "+rows+"x"+columns+"."));
        }
        });
        label82.add(combobox4,c);

        JComboBox combobox1 = new JComboBox(this.selection1);
        combobox1.setPreferredSize(new Dimension(180, 30));
        c.fill = GridBagConstraints.EAST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.EAST;         // default
        c.insets = new Insets(2,0,0,5);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 6;                                // 2 third row

        combobox1.setBackground(Color.white);
        combobox1.setSelectedIndex(0);

        combobox1.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            representation = (String)combobox1.getSelectedItem();
            label7.setText((english) ? (representation+" selected.")
                                     : (representation+" seleccionada."));
            if(representation.equals(selection1[3])){
                c.fill = GridBagConstraints.WEST;
                c.ipady = 5;                                // 0 reset to default
                c.ipadx = 0;                                // 0 reset to default
                c.weightx = 0.0;
                c.weighty = 0.0;                            // 1.0 request any extra vertical space
                c.anchor = GridBagConstraints.WEST;         // default
                c.insets = new Insets(2,5,0,0);             // top padding
                c.gridx = 1;                                // 1 aligned with button 2
                c.gridwidth = 1;                            // 2 2 columns wide
                c.gridy = 7;                                // 2 third row
                v.add(label81, c);
                c.fill = GridBagConstraints.EAST;
                c.ipady = 0;                                // 0 reset to default
                c.ipadx = 0;                                // 0 reset to default
                c.weightx = 0.0;
                c.weighty = 0.0;                            // 1.0 request any extra vertical space
                c.anchor = GridBagConstraints.EAST;         // default
                c.insets = new Insets(2,0,0,5);             // top padding
                c.gridx = 0;                                // 1 aligned with button 2
                c.gridwidth = 1;                            // 2 2 columns wide
                c.gridy = 7;                                // 2 third row
                v.add(label82, c);
                v.repaint();
                label82.repaint();
            }
            else {
                v.remove(label81);
                v.remove(label82);
                v.repaint();
            }
        }
        });

        this.add(combobox1, c);
                
        JLabel label9 = new JLabel((english) ? ("Select frame duration.")
                                             : ("Seleccionar duración de fotogramas."));
        c.fill = GridBagConstraints.WEST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.WEST;       // default
        c.insets = new Insets(2,5,0,0);             // top padding
        c.gridx = 1;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 8;                                // 2 third row

        label9.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 12));
        
        this.add(label9, c);

        JComboBox combobox2 = new JComboBox(this.selection2);
        combobox2.setPreferredSize(new Dimension(180, 30));
        c.fill = GridBagConstraints.EAST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.EAST;       // default
        c.insets = new Insets(2,0,0,5);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 8;                                // 2 third row

        combobox2.setBackground(Color.white);
        combobox2.setSelectedIndex(4);

        combobox2.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            fps = Integer.parseInt((String)combobox2.getSelectedItem());
            label9.setText((english) ? ("Frames last "+fps+" seconds.")
                                     : ("Los fotogramas duran "+fps+" segundos."));
        }
        });

        this.add(combobox2, c);
        
        JLabel label10 = new JLabel("");
        c.fill = GridBagConstraints.WEST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weightx = 0.0;
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.WEST;       // default
        c.insets = new Insets(2,5,0,0);             // top padding
        c.gridx = 1;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 9;                                // 2 third row

        label10.setFont(new java.awt.Font("SANS_SERIF",Font.BOLD, 12));
        
        this.add(label10, c);

        JButton button3 = (english) ? new JButton("Generate video")
                                    : new JButton("Generar video");
        button3.setPreferredSize(new Dimension(180, 30));
        c.fill = GridBagConstraints.EAST;
        c.ipady = 5;                                // 0 reset to default
        c.ipadx = 0;                                // 0 reset to default
        c.weighty = 0.0;                            // 1.0 request any extra vertical space
        c.anchor = GridBagConstraints.EAST;       // default
        c.insets = new Insets(2,0,0,5);             // top padding
        c.gridx = 0;                                // 1 aligned with button 2
        c.gridwidth = 1;                            // 2 2 columns wide
        c.gridy = 9;                                // 2 third row

        button3.setBackground(Color.white);

        button3.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            if(directory_path.isBlank())
                label10.setText((english) ? ("ERROR: First you must select an execution trace")
                                          : ("ERROR: Primero se debe seleccionar una traza de ejecución"));
            else if(representation.isBlank())
                label10.setText((english) ? ("ERROR: First you must select a graphical representation")
                                          : ("ERROR: Primero se debe seleccionar una representación gráfica"));
            else {
                label10.setText((english) ? ("Generating video...")
                                          : ("Generando video..."));
                try {
                    MuLexer lexer = new MuLexer(CharStreams.fromPath​(Path.of(directory_path)));
                    MuParser parser = new MuParser(new CommonTokenStream(lexer));
                    ParseTree tree = parser.parse();
                    EvalVisitor visitor;
                    if(representation.equals(selection1[3]))
                        visitor = new EvalVisitor(FilenameUtils.removeExtension(filename),representation+" "+rows+" "+columns,false);
                    else
                        visitor = new EvalVisitor(FilenameUtils.removeExtension(filename),representation,false);
                    visitor.visit(tree);
                    
                    if(visitor.frame_generator.status() == 0) 
                    {
                        label10.setText((english) ? ("ERROR: Selected graphical representation is incompatible")
                                                  : ("ERROR: Representación gráfica seleccionada incompatible"));
                    }
                    else 
                    {
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "MP4 Files", "mp4");
                        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.dir")));
                        chooser.setSelectedFile(new File("video.mp4"));
                        chooser.setFileFilter(filter);
                        String output_directory_path = System.getProperty("user.dir")+"/video.mp4";
                        if(chooser.showSaveDialog(ventana.getContentPane()) == JFileChooser.APPROVE_OPTION) {
                            output_directory_path = chooser.getSelectedFile().getAbsolutePath();
                            VideoOutput output = new VideoOutput(output_directory_path,fps);
                            for (BufferedImage image : visitor.frame_generator.getFrames(chooser.getSelectedFile().getParent(),chooser.getSelectedFile().getName()))
                                output.encoder.encodeImage(image);
                            output.encoder.finish();
                            label10.setText((english) ? ("Success: Video generated")
                                                      : ("Éxito: Video generado"));
                        } else
                            label10.setText((english) ? ("Generate video")
                                                      : ("Generar video"));
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(windowMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        });

        this.add(button3, c);

    }
    
    public void subM(int i) throws IOException{
        
        ventana.getContentPane().removeAll();
        ventana.revalidate();
        ventana.pack();
        
    }
    
}
