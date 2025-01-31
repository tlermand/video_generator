package analyzer;

import analyzer.MuParser.ExprContext;
import analyzer.MuParser.ExprsContext;
import java.util.ArrayList;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import video_generator.Frames;

public class EvalVisitor extends MuBaseVisitor<Value> {
    
    public ArrayList<String> executionTrace = new ArrayList<>();
    private Map<String, Value> memory = new HashMap<>();
    
    private String root;
    private String target;
    
    // used to compare floating point numbers
    public static final double SMALL_VALUE = 0.00000000001;

    private static final int NO_VISIT_IN_PROGRESS = 0;
    private static final int DEFINITION_IN_PROGRESS = 1;
    private static final int DECLARATION_IN_PROGRESS = 2;
    private static final int ASSIGNMENT_IN_PROGRESS = 3;
    private static final int METHOD_IN_PROGRESS = 4;
    private static final int STATUS_IN_PROGRESS = 5;
    private boolean root_done = false;
    private int status;
    private final ArrayList<String> definition_lines;
    private final ArrayList<String> declaration_lines;
    private final ArrayList<String> assigment_lines;
    private final ArrayList<String> method_lines;
    private final ArrayList<String> status_lines;
    
    private final ArrayList<String> frame_data;
    private final ArrayList<ArrayList<String>> graph_data;
    public final Frames frame_generator;
    private ArrayList<String> last_graph;
    
    // parameters
    private final boolean english = true;
    
    public EvalVisitor(String titulo, String graph_representation, boolean avatar){
        this.status = NO_VISIT_IN_PROGRESS;
        
        this.memory.put("SET_INITIAL", new Value("SET_INITIAL"));
        this.memory.put("SET_TARGET", new Value("SET_TARGET"));
        this.memory.put("ADD", new Value("ADD"));
        this.memory.put("REMOVE_FIRST", new Value("REMOVE_FIRST"));
        this.memory.put("EXPLORE_NODE", new Value("EXPLORE_NODE"));
        
        this.root = new String();
        this.target = new String();
        
        this.definition_lines = new ArrayList<>();
        this.declaration_lines = new ArrayList<>();
        this.assigment_lines = new ArrayList<>();
        this.method_lines = new ArrayList<>();
        this.status_lines = new ArrayList<>();
        
        this.frame_data = new ArrayList<>();
        this.graph_data= new ArrayList<>();
        this.frame_generator = new Frames(graph_representation, avatar);
        this.last_graph = new ArrayList<String>();
        
        ArrayList<String> title_lines=new ArrayList<>(List.of(
                "Automatic generation of explanatory videos",
                "from execution traces.",
                " ",
                " ",
                " ",
                " ",
                titulo
        ));
        this.frame_generator.printTitleFrame(title_lines);
        ArrayList<String> contents_lines=new ArrayList<>(
                (english) ?
                List.of(
                " 1. Definitions",
                " 2. Declarations",
                " 3. Search",
                " 4. Result"
                )
                :
                List.of(
                " 1. Definición de estructuras de datos ",
                " 2. Declaración de variables",
                " 3. Proceso de búsqueda",
                " 4. Resultado"
                )
        );
        this.frame_generator.printContentsFrame(contents_lines);
    }
    
    private void printLines() {
        if(!this.definition_lines.isEmpty())
            this.frame_generator.printDataStructuresFrame(this.definition_lines);
        if(!this.declaration_lines.isEmpty())
            this.frame_generator.printVariableDeclarationFrame(this.declaration_lines,this.graph_data);
        if(!this.assigment_lines.isEmpty()) {
            if(!graph_data.isEmpty())
                this.last_graph = graph_data.get(0);
            this.frame_generator.printQueueFrame(this.assigment_lines,this.graph_data);
        }
        if(!this.method_lines.isEmpty())
            this.frame_generator.printMethodFrame(this.method_lines);
        if(!this.status_lines.isEmpty() && this.status != STATUS_IN_PROGRESS) {
            this.frame_generator.printStatusFrame(this.status_lines,(this.graph_data.isEmpty()) ? this.last_graph : this.graph_data.get(0));
        }
    }
    
    @Override
    public Value visitCommemnt(MuParser.CommemntContext ctx) {
        String comment = ctx.COMMENT().getText();
        //for(TerminalNode word : ctx.)
        this.executionTrace.add(ctx.COMMENT().toStringTree());
        return new Value(String.valueOf(comment));
    }
    
    @Override
    public Value visitDefinition(MuParser.DefinitionContext ctx) {
        
        if(this.status != DEFINITION_IN_PROGRESS) {
            this.printLines();
            this.status = DEFINITION_IN_PROGRESS;
        }
        
        String definition;
        if(ctx.LIST() == null)
            definition = (english) ? "STATE defined as a node composed of "
                                   : ctx.STATE().getText() + " definido como un nodo compuesto por ";
        else
            definition = (english) ? "LIST defined as a collection consisting of a "
                                   : ctx.LIST().getText() + " definida como una colección compuesta por un ";
            
        if(!ctx.expr().isEmpty()) {
            String connector = (ctx.COMMA().isEmpty()) ? " " : ", ";
            Iterator<MuParser.ExprContext> iterator = ctx.expr().iterator();
            definition = definition + iterator.next().getText();
            while(iterator.hasNext())
                definition = definition + connector + iterator.next().getText();
        }
        this.definition_lines.add(definition);
        
        this.executionTrace.add(definition);
        return new Value(String.valueOf(definition));
    }
    
    @Override
    public Value visitDeclaration(MuParser.DeclarationContext ctx) {
        
        if(this.status != DECLARATION_IN_PROGRESS) {
            this.printLines();
            this.status = DECLARATION_IN_PROGRESS;
        }
        
        String declaration;
        if(ctx.LIST() == null)
            declaration = (english) ? "A variable of type STATE \"" +ctx.ID().getText() + "\" is declared " 
                                    : "Se declara una variable " + ctx.STATE().getText() + " " +ctx.ID().getText() + " ";
        else
            declaration = (english) ? "A variable of type LIST \"" +ctx.ID().getText() + "\" is declared " 
                                    : "Se declara una variable " + ctx.STATE().getText() + " " +ctx.ID().getText() + " ";
        this.declaration_lines.add(declaration);
        
        this.executionTrace.add(declaration);
        String id = ctx.ID().getText();
        
        Value value;
        if(english)
        {
            value = (ctx.LIST() == null) ? new Value("STATE") : new Value("LIST");
        }
        else
        {
            value = (ctx.LIST() == null) ? new Value(ctx.STATE().getText()) : new Value(ctx.LIST().getText());
        }
        return this.memory.put(id, value);
    }
    
    // assignment/id overrides
    @Override
    public Value visitAssignment(MuParser.AssignmentContext ctx) {
        
        String assignment = new String();
        switch(this.status) {
            case NO_VISIT_IN_PROGRESS:
                this.printLines();
                this.status = ASSIGNMENT_IN_PROGRESS;
                break;
            case DECLARATION_IN_PROGRESS:
                if(ctx.method() == null) {
                    if(!ctx.OSQBRA().isEmpty())
                        assignment = assignment + "[";
                    if(!ctx.expr().isEmpty()) {
                        String connector = (ctx.COMMA().isEmpty()) ? " " : ",";
                        if(ctx.method() == null) {
                            if(!ctx.expr().isEmpty()) {
                                Iterator<MuParser.ExprContext> iterator = ctx.expr().iterator();
                                int i = 1;
                                while(iterator.hasNext()) {
                                    ArrayList<String> graph = new ArrayList<>();
                                    String next = iterator.next().getText();
                                    StringTokenizer st1 = new StringTokenizer(next, "\"");
                                    int j = 1;
                                    assignment = (i==1&&j==1) ? assignment + next : assignment + connector + next;
                                    while (st1.hasMoreTokens()) {
                                        String words = st1.nextToken();
                                        StringTokenizer st2 = new StringTokenizer(words, "{}[],");
                                        String word = new String();
                                        while (st2.hasMoreTokens()) {
                                            if(word.isBlank())
                                                word = word + st2.nextToken();
                                            else
                                                word = word + ", " + st2.nextToken();
                                        }
                                        if(!word.isBlank() && !word.equals("null"))
                                            graph.add(word);
                                        ++j;
                                    }
                                    this.graph_data.add(graph);
                                    ++i;
                                }
                            }
                        }
                    }
                    if(!ctx.CSQBRA().isEmpty())
                        assignment = assignment + "]";
                }
                if(english)
                {
                    this.declaration_lines.add(this.declaration_lines.remove(this.declaration_lines.size()-1) + " and " + assignment + " is assigned to it.");
                    this.executionTrace.add(" and " + assignment + " is assigned to it.");
                }
                else
                {
                    this.declaration_lines.add(this.declaration_lines.remove(this.declaration_lines.size()-1) + " y se le asigna " + assignment);
                    this.executionTrace.add(" y se le asigna " + assignment);
                }
                break;
            case STATUS_IN_PROGRESS:
                this.printLines();
                
                if(ctx.method() == null) {
                    if(!ctx.expr().isEmpty()) {
                        Iterator<MuParser.ExprContext> iterator = ctx.expr().iterator();
                        int i = 1;
                        while(iterator.hasNext()) {
                            ArrayList<String> graph = new ArrayList<>();
                            String next = iterator.next().getText();
                            StringTokenizer st1 = new StringTokenizer(next, "\"");
                            int j = 1;
                            while (st1.hasMoreTokens()) {
                                String words = st1.nextToken();
                                StringTokenizer st2 = new StringTokenizer(words, "{}[],");
                                String word = new String();
                                while (st2.hasMoreTokens()) {
                                    if(word.isBlank())
                                        word = word + st2.nextToken();
                                    else
                                        word = word + ", " + st2.nextToken();
                                }
                                if(!word.isBlank() && !word.equals("null"))
                                    graph.add(word);
                                ++j;
                            }
                            this.graph_data.add(graph);
                            ++i;
                        }
                    }
                }
                
                this.status = ASSIGNMENT_IN_PROGRESS;
                this.printLines();
                this.graph_data.clear();
                break;
            default:
                this.printLines();
                this.status = ASSIGNMENT_IN_PROGRESS;
                if(!this.frame_data.isEmpty()) {
                    if(this.memory.containsKey("ASSIGN")) {
                        this.memory.remove("ASSIGN");
                        if(english)
                        {
                            this.assigment_lines.add(this.frame_data.remove(0) + "and is assigned to");
                            this.assigment_lines.add(ctx.ID().getText() + " to explore the next action.");
                            this.assigment_lines.add(ctx.ID().getText() + " now contains:");
                        }
                        else
                        {
                            this.assigment_lines.add(this.frame_data.remove(0) + "y se le asigna a");
                            this.assigment_lines.add(ctx.ID().getText() + " para explorar la siguiente acción.");
                            this.assigment_lines.add(ctx.ID().getText() + " ahora contiene:");
                        }
                    } else {
                        this.assigment_lines.add(this.frame_data.remove(0));
                        if (english)
                            this.assigment_lines.add(ctx.ID().getText() + " now contains:");
                        else 
                            this.assigment_lines.add(ctx.ID().getText() + " ahora contiene:");
                    }
                } 
                
                if(ctx.method() == null) {
                    if(!ctx.expr().isEmpty()) {
                        Iterator<MuParser.ExprContext> iterator = ctx.expr().iterator();
                        int i = 1;
                        while(iterator.hasNext()) {
                            ArrayList<String> graph = new ArrayList<>();
                            String next = iterator.next().getText();
                            StringTokenizer st1 = new StringTokenizer(next, "\"");
                            int j = 1;
                            while (st1.hasMoreTokens()) {
                                String words = st1.nextToken();
                                StringTokenizer st2 = new StringTokenizer(words, "{}[],");
                                String word = new String();
                                while (st2.hasMoreTokens()) {
                                    if(word.isBlank())
                                        word = word + st2.nextToken();
                                    else
                                        word = word + ", " + st2.nextToken();
                                }
                                if(!word.isBlank() && !word.equals("null"))
                                    graph.add(word);
                                ++j;
                            }
                            this.graph_data.add(graph);
                            ++i;
                        }
                    }
                } else {
                    this.memory.put("ASSIGN", new Value(ctx.ID().getText()));
                    this.visit(ctx.method());
                }
                
                this.printLines();
                this.status = ASSIGNMENT_IN_PROGRESS;
                this.graph_data.clear();
                break;
        }
        
        String id = ctx.ID().getText();
        return this.memory.get(id);
    }
    
    @Override
    public Value visitMethod(MuParser.MethodContext ctx) {
        
        if(this.status != METHOD_IN_PROGRESS) {
            this.printLines();
            this.status = METHOD_IN_PROGRESS;
            this.graph_data.clear();
        }
        
        String method = new String();
        if(ctx.ID().size() == 1) {
            if(ctx.ID(0).getText().equals("SET_INITIAL")) {
                if(english)
                    this.root = "Variable " + this.memory.get(ctx.expr(0).getText()).value + " " + ctx.expr(0).getText() + " is assigned as the starting point of the search.";
                else
                    this.root = "Se asigna la variable " + this.memory.get(ctx.expr(0).getText()).value + " " + ctx.expr(0).getText() + " como punto de partida de la búsqueda.";
                this.executionTrace.add(this.root);
                if(!this.target.isBlank()) {
                    if(english)
                    {
                        this.method_lines.add("2. Declarations");
                        this.method_lines.add("The search parameters are defined below:");
                    }
                    else
                    {
                        this.method_lines.add("2. Definición de parámetros de búsqueda");
                        this.method_lines.add("A continuación se definen los parámetros de búsqueda:");
                    }
                    this.method_lines.add(this.root);
                    this.method_lines.add(this.target);
                }
            }
            if(ctx.ID(0).getText().equals("SET_TARGET")) {
                if(english)
                    this.target = "Variable " + this.memory.get(ctx.expr(0).getText()).value + " " + ctx.expr(0).getText() + " is assigned as the search target.";
                else
                    this.target = "Se asigna la variable " + this.memory.get(ctx.expr(0).getText()).value + " " + ctx.expr(0).getText() + " como objetivo de la búsqueda.";
                this.executionTrace.add(this.target);
                if(!this.root.isBlank()) {
                    if(english)
                    {
                        this.method_lines.add("2. Declarations");
                        this.method_lines.add("The search parameters are defined below:");
                    }
                    else
                    {
                        this.method_lines.add("2. Definición de parámetros de búsqueda");
                        this.method_lines.add("A continuación se definen los parámetros de búsqueda:");
                    }
                    this.method_lines.add(this.root);
                    this.method_lines.add(this.target);
                }
            }
        }
        
        if(ctx.ID().size() == 2) {
            if(ctx.ID(1).getText().equals("ADD")) {
                String expr;
                if(english)
                    expr = ctx.expr(0).getText().equals("NEW_NODE") ? "A new state" : ctx.expr(0).getText();
                else
                    expr = ctx.expr(0).getText().equals("NEW_NODE") ? "un nuevo estado" : ctx.expr(0).getText();
                frame_data.add(
                        (english) ? expr + " is added to LIST " + ctx.ID(0).getText() + "."
                                  : "Se añade " + expr + " a la LISTA " + ctx.ID(0).getText() + "."
                );
            }
            if(ctx.ID(1).getText().equals("REMOVE_FIRST")) {
                frame_data.add(
                        (english) ? "The first element of " + ctx.ID(0).getText() + " is removed "
                                  : "Se remueve el primer elemento de " + ctx.ID(0).getText() + " ");
            }
        }
        
        this.root_done = true;
        
        return new Value(method);
    }
    
    @Override
    public Value visitStatus(MuParser.StatusContext ctx) {
        if(this.status != STATUS_IN_PROGRESS) {
            this.status = STATUS_IN_PROGRESS;
        }
        if(ctx.exprs().size() > 1){
            if(english)
            {
                this.status_lines.add("3. Search");
                this.status_lines.add("The explored state \"" + ctx.ID(0).getText() + "\" is evaluated and it is determined");
                this.status_lines.add(" that it does not match " + ctx.ID(1).getText() + ".");
                this.status_lines.add("Other possibilities continue to be explored.");
            }
            else
            {
                this.status_lines.add("3. Proceso de búsqueda");
                this.status_lines.add("Se evalúa el estado explorado \"" + ctx.ID(0).getText() + "\" y se determina que no");
                this.status_lines.add("corresponde a " + ctx.ID(1).getText() + ".");
                this.status_lines.add("Se continúa explorando otras posibilidades.");
            }
        }
        else{
            if(english)
            {
                this.status_lines.add("4. Result");
                this.status_lines.add("The explored state \"" + ctx.ID(0).getText() + "\" is evaluated and it is determined");
                this.status_lines.add(" that it does match " + ctx.ID(1).getText() + ".");
                this.status_lines.add("Search completed.");
            }
            else
            {
                this.status_lines.add("4. Resultado");
                this.status_lines.add("Se evalúa el estado explorado \"" + ctx.ID(0).getText() + "\" y se determina que");
                this.status_lines.add("corresponde a " + ctx.ID(1).getText() + ".");
                this.status_lines.add("Búsqueda completada.");
            }
        }
        
        String status = ctx.ID(0).getText();
        for(ExprsContext word : ctx.exprs()){
            status = status+" "+word.getText();
        }
        status = status+" "+ctx.ID(1).getText();
        this.executionTrace.add(status);
        
        String id = ctx.ID(1).getText();
        if(!this.memory.containsKey(id))
            throw new RuntimeException("Undeclared variable: " + id);
        id = ctx.ID(0).getText();
        return this.memory.get(id);
    }
    
    @Override
    public Value visitBraExpr(MuParser.BraExprContext ctx) {
        if(ctx.exprs().isEmpty())
            return null;
        ArrayList<String> exprs = new ArrayList<>();
        for(ExprsContext exp : ctx.exprs())
            exprs.addAll(visit(exp).asStringArray());
        return new Value(exprs);
    }
    
    @Override
    public Value visitSqbraExpr(MuParser.SqbraExprContext ctx) {
        if(ctx.exprs().isEmpty())
            return null;
        ArrayList<String> exprs = new ArrayList<>();
        for(ExprsContext exp : ctx.exprs())
            exprs.add(exp.getText());
        return new Value(exprs);
    }
    
    @Override
    public Value visitExprs(MuParser.ExprsContext ctx) {
        ArrayList<String> exprs = new ArrayList<>();
        for(ExprContext exp : ctx.expr()){
            Value value = visit(exp);
            if(value != null)
                if(value.value instanceof String)
                    exprs.add(value.asString());
                else
                    exprs.addAll(value.asStringArray());
        }
        return new Value(exprs);
    }

    @Override
    public Value visitIdAtom(MuParser.IdAtomContext ctx) {
        return new Value(ctx.ID().getText());
    }

    // atom overrides
    @Override
    public Value visitStringAtom(MuParser.StringAtomContext ctx) {
        String str = ctx.getText();
        // strip quotes
        str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
        return new Value(str);
    }
    
    @Override
    public Value visitPointerExpr(MuParser.PointerExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Value visitNumberAtom(MuParser.NumberAtomContext ctx) {
        return new Value(null);
    }

    @Override
    public Value visitBooleanAtom(MuParser.BooleanAtomContext ctx) {
        return new Value(null);
    }

    @Override
    public Value visitNilAtom(MuParser.NilAtomContext ctx) {
        return new Value(null);
    }

    @Override
    public Value visitPowExpr(MuParser.PowExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(null);
    }

    @Override
    public Value visitNotExpr(MuParser.NotExprContext ctx) {
        Value value = this.visit(ctx.expr());
        return new Value(null);
    }

    @Override
    public Value visitMultiplicationExpr(@NotNull MuParser.MultiplicationExprContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case MuParser.MULT:
                return new Value(left.asDouble() * right.asDouble());
            case MuParser.DIV:
                return new Value(left.asDouble() / right.asDouble());
            case MuParser.MOD:
                return new Value(left.asDouble() % right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + MuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override
    public Value visitAdditiveExpr(@NotNull MuParser.AdditiveExprContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case MuParser.PLUS:
                return left.isDouble() && right.isDouble() ?
                        new Value(left.asDouble() + right.asDouble()) :
                        new Value(left.asString() + right.asString());
            case MuParser.MINUS:
                return new Value(left.asDouble() - right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + MuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override
    public Value visitRelationalExpr(@NotNull MuParser.RelationalExprContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case MuParser.LT:
                return new Value(left.asDouble() < right.asDouble());
            case MuParser.LTEQ:
                return new Value(left.asDouble() <= right.asDouble());
            case MuParser.GT:
                return new Value(left.asDouble() > right.asDouble());
            case MuParser.GTEQ:
                return new Value(left.asDouble() >= right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + MuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override
    public Value visitEqualityExpr(@NotNull MuParser.EqualityExprContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case MuParser.EQ:
                return left.isDouble() && right.isDouble() ?
                        new Value(Math.abs(left.asDouble() - right.asDouble()) < SMALL_VALUE) :
                        new Value(left.equals(right));
            case MuParser.NEQ:
                return left.isDouble() && right.isDouble() ?
                        new Value(Math.abs(left.asDouble() - right.asDouble()) >= SMALL_VALUE) :
                        new Value(!left.equals(right));
            default:
                throw new RuntimeException("unknown operator: " + MuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override
    public Value visitAndExpr(MuParser.AndExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(left.asBoolean() && right.asBoolean());
    }

    @Override
    public Value visitOrExpr(MuParser.OrExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(left.asBoolean() || right.asBoolean());
    }

    // log override
    @Override
    public Value visitLog(MuParser.LogContext ctx) {
        Value value = this.visit(ctx.expr());
        return value;
    }

    // if override
    @Override
    public Value visitIf_stat(MuParser.If_statContext ctx) {

        List<MuParser.Condition_blockContext> conditions =  ctx.condition_block();

        boolean evaluatedBlock = false;

        for(MuParser.Condition_blockContext condition : conditions) {

            Value evaluated = this.visit(condition.expr());

            if(evaluated.asBoolean()) {
                evaluatedBlock = true;
                // evaluate this block whose expr==true
                this.visit(condition.stat_block());
                break;
            }
        }

        if(!evaluatedBlock && ctx.stat_block() != null) {
            // evaluate the else-stat_block (if present == not null)
            this.visit(ctx.stat_block());
        }

        return Value.VOID;
    }

    // while override
    @Override
    public Value visitWhile_stat(MuParser.While_statContext ctx) {

        Value value = this.visit(ctx.expr());

        while(value.asBoolean()) {

            // evaluate the code block
            this.visit(ctx.stat_block());

            // evaluate the expression
            value = this.visit(ctx.expr());
        }

        return Value.VOID;
    }
}