import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.filechooser.FileSystemView;


public class Trace {
    
    private final File execution_trace;
    private final Charset charset;
    
    private Map<String, Object> declaredVariables;
    private String target_variable_name;
    
    public Trace() {
        String home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        String filename = "execution_trace";
        String pathname = home+"\\"+filename+".txt";
        
        this.execution_trace = new File(pathname);
        this.charset = Charset.forName("UTF-8");
        
        try(
            FileWriter fw = new FileWriter(this.execution_trace, this.charset, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)
            )
        {
            out.print("");
        } catch (IOException e) {
            System.err.println("execution trace read error");
        }
        
        declaredVariables = new HashMap<>();
        target_variable_name = new String();
    }
    
    public void defineState(String value_name, String descriptor_name, String previous_value_name) {
        try(
            FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)
            )
        {
            out.println( "Type STATE = {" + value_name.replace(' ', '_').toUpperCase() + ", " + descriptor_name.replace(' ', '_').toUpperCase() + ", " + previous_value_name.replace(' ', '_').toUpperCase() + "}" );
        } catch (IOException e) {
            System.err.println("execution trace read error");
        }
    }
    
    public void defineList() {
        try(
            FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)
            )
        {
            out.println("Type LIST = [SET OF STATES]");
        } catch (IOException e) {
            System.err.println("execution trace read error");
        }
    }
    
    private boolean declareVariable(Object variable, String variable_name) {
        String declare = variable_name + ": type ";
        try(
            FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)
            )
        {
            if(variable instanceof Node) {
                out.println(declare + "STATE");
                this.declaredVariables.put(variable_name, variable);
                return true;
            }
            if(variable instanceof Collection) {
                out.println(declare + "LIST");
                this.declaredVariables.put(variable_name, variable);
                return true;
            }
        } catch (IOException e) {
            System.err.println("execution trace read error");
        }
        return false;
    }
    
    public void assignVariable(String variable_name, Node new_variable) {
        variable_name = variable_name.toLowerCase();
        if(!this.declaredVariables.containsKey(variable_name))
            this.declareVariable((new_variable != null) ? new_variable : Node.EMPTY_NODE, variable_name);
        else
            this.declaredVariables.put(variable_name, new_variable);
        this.assignVariable(variable_name, (Object) new_variable);
    }
    
    public void assignVariable(String variable_name, Collection new_variable) {
        variable_name = variable_name.toLowerCase();
        if(!this.declaredVariables.containsKey(variable_name))
            this.declareVariable((new_variable != null) ? new_variable : Collections.EMPTY_LIST, variable_name);
        else
            this.declaredVariables.put(variable_name, new_variable);
        this.assignVariable(variable_name, (Object) new_variable);
    }
    
    public void temporalVariable(String variable_name, Node new_variable) {
        variable_name = variable_name.toLowerCase();
        this.assignVariable(variable_name, (Object) new_variable);
    }
    
    private void assignVariable(String variable_name, Object new_variable) {
        try(
            FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)
            )
        {
            out.println(variable_name + " = " + ( (new_variable != null) ? new_variable.toString().toLowerCase() : "null") );
        } catch (IOException e) {
            System.err.println("execution trace read error");
        }
    }
    
    public void defineRoot(String root_variable_name) {
        root_variable_name = root_variable_name.toLowerCase();
        if(this.declaredVariables.containsKey(root_variable_name)) {
            try(
                FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)
                )
            {
                out.println("SET_INITIAL(" + root_variable_name + ")");
            } catch (IOException e) {
                System.err.println("execution trace read error");
            }
        } else {
            throw new RuntimeException(root_variable_name + " must be declared/assign first.");
        }
    }
    
    public void defineTarget(String target_variable_name) {
        target_variable_name = target_variable_name.toLowerCase();
        if(this.declaredVariables.containsKey(target_variable_name)) {
            this.target_variable_name = target_variable_name;
            try(
                FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)
                )
            {
                out.println("SET_TARGET(" + target_variable_name + ")");
            } catch (IOException e) {
                System.err.println("execution trace read error");
            }
        } else {
            throw new RuntimeException(target_variable_name + " must be declared/assign first.");
        }
    }
    
    public void addNode(String list_name) {
        list_name = list_name.toLowerCase();
        if(this.declaredVariables.containsKey(list_name)) {
            try(
                FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)
                )
            {
                out.println(list_name + ".ADD(NEW_NODE)");
            } catch (IOException e) {
                System.err.println("execution trace read error");
            }
            this.assignVariable(list_name, declaredVariables.get(list_name));
        } else
            throw new RuntimeException(list_name + " must be declared/assign first.");
    }
    
    public void addNode(String list_name, String added_variable_name) {
        list_name = list_name.toLowerCase();
        added_variable_name = added_variable_name.toLowerCase();
        if(this.declaredVariables.containsKey(list_name))
            if(this.declaredVariables.containsKey(added_variable_name)) {
                try(
                    FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)
                    )
                {
                    out.println(list_name + ".ADD(" + added_variable_name + ")");
                } catch (IOException e) {
                    System.err.println("execution trace read error");
                }
                this.assignVariable(list_name, declaredVariables.get(list_name));
            } else {
                throw new RuntimeException(added_variable_name + " must be declared/assign first.");
            }
        else
            throw new RuntimeException(list_name + " must be declared/assign first.");
    }
    
    public void removeFirstElement(String node_name, Node node, String list_name) {
        node_name = node_name.toLowerCase();
        list_name = list_name.toLowerCase();
        if(!node_name.isBlank())
            if(this.declaredVariables.containsKey(list_name)) {
                try(
                    FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)
                    )
                {
                    out.println(node_name + " = " + list_name + ".REMOVE_FIRST()");
                } catch (IOException e) {
                    System.err.println("execution trace read error");
                }
                this.assignVariable(node_name, node);
                this.assignVariable(list_name, declaredVariables.get(list_name));
            } else {
                throw new RuntimeException(list_name + " must be declared/assign first.");
            }
        else
            throw new RuntimeException(node_name + " field can not be blank.");
    }
      
    public void finalStateReached(String node_name) {
        if(!this.target_variable_name.isBlank())
            if(this.declaredVariables.containsKey(node_name)) {
                String result = (declaredVariables.get(target_variable_name).equals(declaredVariables.get(node_name))) ? " is " : " is not ";
                try(
                    FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)
                    )
                {
                    out.println(node_name + result + this.target_variable_name);
                } catch (IOException e) {
                    System.err.println("execution trace read error");
                }
                this.assignVariable(node_name, declaredVariables.get(node_name));
            } else {
                throw new RuntimeException(node_name + " must be declared/assign first.");
            }
        else
            throw new RuntimeException("A target state for the algorithm must be defined. Call defineTarget(target_variable_name)");
    }
    
    public void finalStateReached(String node_name, Node node) {
        if(!this.target_variable_name.isBlank()) {
            String result = (declaredVariables.get(target_variable_name).equals(node)) ? " is " : " is not ";
            try(
                FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)
                )
            {
                out.println(node_name + result + this.target_variable_name);
            } catch (IOException e) {
                System.err.println("execution trace read error");
            }
            this.assignVariable(node_name, (Object) node);
        } else
            throw new RuntimeException("A target state for the algorithm must be defined. Call defineTarget(target_variable_name)");
    }
    
    public void exploreNode(String explored_node_name) {
        explored_node_name = explored_node_name.toLowerCase();
        if(this.declaredVariables.containsKey(explored_node_name))
            try(
                FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)
                )
            {
                out.println("EXPLORE_NODE(" + explored_node_name + ")");
            } catch (IOException e) {
                System.err.println("execution trace read error");
            }
        else
            throw new RuntimeException(explored_node_name + " field can not be blank.");
    }
    
    public void exploreNode(String explored_node_name, Node node1, Node node2) {
        explored_node_name = explored_node_name.toLowerCase();
        if(this.declaredVariables.containsKey(explored_node_name))
            try(
                FileWriter fw = new FileWriter(this.execution_trace, this.charset, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)
                )
            {
                out.println("EXPLORE_NODE(" + explored_node_name + "):" + 
                            "\n1.- " + node1.toString() +
                            "\n2.- " + node2.toString()
                            );
            } catch (IOException e) {
                System.err.println("execution trace read error");
            }
        else
            throw new RuntimeException(explored_node_name + " field can not be blank.");
    }
}
