// Generated from Calculette.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, NEWLINE=31, 
		WS=32, ENTIER=33, TYPE=34, RETURN=35, IDENTIFIANT=36, LONG_COMMENT=37, 
		SHORT_COMMENT=38;
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_instruction = 2, RULE_expression = 3, 
		RULE_finInstruction = 4, RULE_decl = 5, RULE_assignation = 6, RULE_input = 7, 
		RULE_boucle = 8, RULE_condition = 9, RULE_bloc = 10, RULE_codeif = 11, 
		RULE_codefor = 12, RULE_coderepeat = 13, RULE_fonction = 14, RULE_params = 15, 
		RULE_args = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "calcul", "instruction", "expression", "finInstruction", "decl", 
			"assignation", "input", "boucle", "condition", "bloc", "codeif", "codefor", 
			"coderepeat", "fonction", "params", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'*'", "'/'", "'+'", "'-'", "';'", "'='", "'read('", 
			"'write('", "'while'", "'&&'", "'||'", "'!'", "'=='", "'!='", "'>'", 
			"'>='", "'<'", "'<='", "'true'", "'false'", "'{'", "'}'", "'if'", "'else'", 
			"'for'", "'repeat'", "'until'", "','", null, null, null, null, "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NEWLINE", "WS", "ENTIER", 
			"TYPE", "RETURN", "IDENTIFIANT", "LONG_COMMENT", "SHORT_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    private TablesSymboles tablesSymboles = new TablesSymboles();

	    private String mvapEval(String op) {
	        if ( op.equals("*") ){
	            return "MUL";
	        } else if ( op.equals("+") ){
	            return "ADD";
	        } else if (op.equals("-")) {
	            return "SUB";
	        } else if (op.equals("/")) {
	            return "DIV";
	        } else {
	           System.err.println("Opérateur arithmétique incorrect : '"+op+"'");
	           throw new IllegalArgumentException("Opérateur arithmétique incorrect : '"+op+"'");
	        }
	    }

	    private int _cur_label = 1;
	    /** générateur de nom d'étiquettes pour les boucles */
	    private String getNewLabel() { return "B" +(_cur_label++); }

	    private String evalboucle(String cond, String inst) {
	        String labelFin = getNewLabel();
	        String labelDebut = getNewLabel();
	        String sret = "";
	        sret += "LABEL " + labelDebut + "\n";
	        sret += cond;
	        sret += "JUMPF " + labelFin + "\n";
	        sret += inst;
	        sret += "JUMP " + labelDebut + "\n";
	        sret += "LABEL " + labelFin + "\n";
	        return sret;
	    }

	    private String evalCond(String x, String op, String y) {
	        if (op.equals("==")) {
	            return x + y + "EQUAL\n";
	        } else if (op.equals("<")) {
	            return x + y + "INF\n";
	        } else if (op.equals(">")) {
	            return x + y + "SUP\n";
	        } else if (op.equals("!=")) {
	            return x + y + "NEQ\n";
	        } else if (op.equals("<=")) {
	            return x + y + "INFEQ\n";
	        } else if (op.equals(">=")) {
	            return x + y + "SUPEQ\n";
	        } else {
	            System.err.println("Opérateur de comparaison incorrect : '" + op + "'");
	            throw new IllegalArgumentException("Opérateur de comparaison incorrect : '" + op + "'");
	        }
	    }

	    private String evallog(String op) {
	        if (op.equals("&&")) {
	            return "MUL\n";
	        } else if (op.equals("||")) {
	            return "ADD\n";
	        } else if (op.equals("!")) {
	            return "PUSHI 0\nEQUAL\n";
	        } else {
	            System.err.println("Opérateur logique incorrect : '" + op + "'");
	            throw new IllegalArgumentException("Opérateur logique incorrect : '" + op + "'");
	        }
	    }

	    private String evalif(String cond, String instr) {
	        String labelDebut = getNewLabel();
	        String sret = "";
	        sret += cond;
	        sret += "JUMPF " + labelDebut + "\n";
	        sret += instr;
	        sret += "LABEL " + labelDebut + "\n";
	        return sret;
	    }

	    private String evalifelse(String cond, String instr, String instrelse) {
	        String labelFin = getNewLabel();
	        String labelDebutElse = getNewLabel();
	        String sret = "";
	        sret += cond;
	        sret += "JUMPF " + labelDebutElse + "\n";
	        sret += instr;
	        sret += "JUMP " + labelFin + "\n";
	        sret += "LABEL " + labelDebutElse + "\n";
	        sret += instrelse;
	        sret += "JUMP " + labelFin + "\n";
	        sret += "LABEL " + labelFin + "\n";
	        return sret;
	    }

	    private String evalfor(String assignation, String cond, String increment, String instr) {
	        String labelDebutFor = getNewLabel();
	        String labelFinFor = getNewLabel();
	        String sret = "";
	        sret += assignation;
	        sret += "LABEL " + labelDebutFor + "\n";
	        sret += cond;
	        sret += "JUMPF " + labelFinFor + "\n";
	        sret += instr;
	        sret += increment;
	        sret += "JUMP " + labelDebutFor + "\n";
	        sret += "LABEL " + labelFinFor + "\n";
	        return sret;
	    }

	    private String evalRepeat(String instruction, String condition) {
	        String labelDebut = getNewLabel();
	        String sret = "";
	        sret += "LABEL " + labelDebut + "\n";
	        sret += instruction;
	        sret += condition;
	        sret += "JUMPF " + labelDebut + "\n";
	        return sret;
	    }

	public CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CalculetteParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			calcul();
			setState(35);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclContext decl;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCalcul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCalcul(this);
		}
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_calcul);
		 ((CalculContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(37);
					((CalculContext)_localctx).decl = decl();
					 _localctx.code += ((CalculContext)_localctx).decl.code; 
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			 _localctx.code += "JUMP Main\n"; 
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(46);
					match(NEWLINE);
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(52);
				((CalculContext)_localctx).fonction = fonction();
				 _localctx.code += ((CalculContext)_localctx).fonction.code; 
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60);
					match(NEWLINE);
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			 _localctx.code += "LABEL Main\n"; 
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__22) | (1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << NEWLINE) | (1L << ENTIER) | (1L << RETURN) | (1L << IDENTIFIANT))) != 0)) {
				{
				{
				setState(67);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 _localctx.code += "  HALT\n"; 
			}
			_ctx.stop = _input.LT(-1);
			 System.out.println(_localctx.code); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public BlocContext bloc;
		public ExpressionContext expression;
		public AssignationContext assignation;
		public InputContext input;
		public BoucleContext boucle;
		public CodeifContext codeif;
		public CodeforContext codefor;
		public CoderepeatContext coderepeat;
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public BoucleContext boucle() {
			return getRuleContext(BoucleContext.class,0);
		}
		public CodeifContext codeif() {
			return getRuleContext(CodeifContext.class,0);
		}
		public CodeforContext codefor() {
			return getRuleContext(CodeforContext.class,0);
		}
		public CoderepeatContext coderepeat() {
			return getRuleContext(CoderepeatContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CalculetteParser.RETURN, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				((InstructionContext)_localctx).bloc = bloc();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).bloc.code;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				((InstructionContext)_localctx).expression = expression(0);
				setState(81);
				finInstruction();
				 
				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				((InstructionContext)_localctx).assignation = assignation();
				setState(85);
				finInstruction();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code;
				    
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(88);
				((InstructionContext)_localctx).input = input();
				setState(89);
				finInstruction();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).input.code;
				    
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(92);
				((InstructionContext)_localctx).boucle = boucle();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucle.code;
				    
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				((InstructionContext)_localctx).codeif = codeif();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).codeif.code;
				    
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(98);
				((InstructionContext)_localctx).codefor = codefor();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).codefor.code;
				    
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(101);
				((InstructionContext)_localctx).coderepeat = coderepeat();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).coderepeat.code;
				    
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(104);
				match(RETURN);
				setState(105);
				((InstructionContext)_localctx).expression = expression(0);
				setState(106);
				finInstruction();

				        AdresseType at = tablesSymboles.getAdresseType("return");
				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
				        _localctx.code += "STOREL " + at.adresse + "\nRETURN\n";
				        // Tester si entier ou float, si float : STOREL de deux valeurs (décimal et entière) et donc deux return
				    
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(109);
				finInstruction();

				        ((InstructionContext)_localctx).code = "";
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ExpressionContext a;
		public ExpressionContext expression;
		public Token ENTIER;
		public Token IDENTIFIANT;
		public ArgsContext args;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(115);
				match(T__0);
				setState(116);
				((ExpressionContext)_localctx).a = ((ExpressionContext)_localctx).expression = expression(0);
				setState(117);
				match(T__1);
				 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type; 
				}
				break;
			case 2:
				{
				setState(120);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "PUSHI " + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n"; ((ExpressionContext)_localctx).type =  "int"; 
				}
				break;
			case 3:
				{
				setState(122);
				match(T__5);
				setState(123);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "PUSHI -" + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n"; ((ExpressionContext)_localctx).type =  "int"; 
				}
				break;
			case 4:
				{
				setState(125);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(126);
				match(T__0);
				setState(127);
				((ExpressionContext)_localctx).args = args();
				setState(128);
				match(T__1);

				        ((ExpressionContext)_localctx).type =  tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				        // Pour les double, pushi 0.0
				        ((ExpressionContext)_localctx).code =  "PUSHI 0\n";
				        _localctx.code += ((ExpressionContext)_localctx).args.code;
				        _localctx.code += "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
				        for (int i = 0; i < ((ExpressionContext)_localctx).args.size; i++) {
				            _localctx.code += "POP\n";
				        }
				    
				}
				break;
			case 5:
				{
				setState(131);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				            AdresseType at = tablesSymboles.getAdresseType((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse > 0) {
				                ((ExpressionContext)_localctx).code =  "PUSHG " + at.adresse + "\n";
				            } else {
				                ((ExpressionContext)_localctx).code =  "PUSHL " + at.adresse + "\n";
				            }
				            ((ExpressionContext)_localctx).type =  at.type;
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(145);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(136);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__3) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(137);
						((ExpressionContext)_localctx).b = ((ExpressionContext)_localctx).expression = expression(7);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + mvapEval((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)) + "\n"; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type; 
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(140);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(141);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__4 || _la==T__5) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(142);
						((ExpressionContext)_localctx).b = ((ExpressionContext)_localctx).expression = expression(6);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + mvapEval((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)) + "\n"; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type; 
						}
						break;
					}
					} 
				}
				setState(149);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFinInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFinInstruction(this);
		}
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(150);
					_la = _input.LA(1);
					if ( !(_la==T__6 || _la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(153); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public Token ENTIER;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_decl);
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(156);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(157);
				match(T__7);
				setState(158);
				((DeclContext)_localctx).ENTIER = match(ENTIER);
				setState(159);
				finInstruction();

				            tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            _localctx.code += "PUSHI " + (((DeclContext)_localctx).ENTIER!=null?((DeclContext)_localctx).ENTIER.getText():null) + "\n";
				            AdresseType at = tablesSymboles.getAdresseType((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse < 0) {
				                _localctx.code += "STOREL " + at.adresse + "\n";
				            } else {
				                _localctx.code += "STOREG " + at.adresse + "\n";
				            }
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(163);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(164);
				finInstruction();

				            tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext a;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAssignation(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(170);
			match(T__7);
			setState(171);
			((AssignationContext)_localctx).a = expression(0);

			            AdresseType at = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
			            if (at.adresse < 0) {
			                ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREL " + at.adresse + "\n";
			            } else {
			                ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREG " + at.adresse + "\n";
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext a;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInput(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_input);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(T__8);
				setState(175);
				((InputContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(176);
				match(T__1);

				            ((InputContext)_localctx).code =  "READ\n";
				            AdresseType at = tablesSymboles.getAdresseType((((InputContext)_localctx).IDENTIFIANT!=null?((InputContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse < 0) {
				                _localctx.code += "STOREL " + at.adresse + "\n";
				            } else {
				                _localctx.code += "STOREG " + at.adresse + "\n";
				            }
				        
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				match(T__9);
				setState(179);
				((InputContext)_localctx).a = expression(0);
				setState(180);
				match(T__1);

				            ((InputContext)_localctx).code =  ((InputContext)_localctx).a.code;
				            _localctx.code += "WRITE\n";
				            _localctx.code += "POP\n";
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoucleContext extends ParserRuleContext {
		public String code;
		public ConditionContext a;
		public InstructionContext b;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public BoucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBoucle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBoucle(this);
		}
	}

	public final BoucleContext boucle() throws RecognitionException {
		BoucleContext _localctx = new BoucleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_boucle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(T__10);
			setState(186);
			match(T__0);
			setState(187);
			((BoucleContext)_localctx).a = condition(0);
			setState(188);
			match(T__1);
			setState(189);
			((BoucleContext)_localctx).b = instruction();

			        ((BoucleContext)_localctx).code =  evalboucle(((BoucleContext)_localctx).a.code, ((BoucleContext)_localctx).b.code);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public ConditionContext c;
		public ExpressionContext a;
		public Token op;
		public ExpressionContext b;
		public ConditionContext d;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				{
				setState(193);
				match(T__13);
				setState(194);
				((ConditionContext)_localctx).c = condition(4);

				        ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + evallog("!");
				    
				}
				break;
			case T__0:
			case T__5:
			case ENTIER:
			case IDENTIFIANT:
				{
				setState(197);
				((ConditionContext)_localctx).a = expression(0);
				setState(198);
				((ConditionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
					((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(199);
				((ConditionContext)_localctx).b = expression(0);

				        ((ConditionContext)_localctx).code =  evalCond(((ConditionContext)_localctx).a.code, (((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null), ((ConditionContext)_localctx).b.code);
				    
				}
				break;
			case T__20:
				{
				setState(202);
				match(T__20);
				 ((ConditionContext)_localctx).code =  "PUSHI 1\n"; 
				}
				break;
			case T__21:
				{
				setState(204);
				match(T__21);
				 ((ConditionContext)_localctx).code =  "PUSHI 0\n"; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(220);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(218);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(208);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(209);
						match(T__11);
						setState(210);
						((ConditionContext)_localctx).d = condition(7);

						                  ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).d.code + evallog("&&");
						              
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(213);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(214);
						match(T__12);
						setState(215);
						((ConditionContext)_localctx).d = condition(6);

						                  ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).d.code +evallog("||");
						              
						}
						break;
					}
					} 
				}
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBloc(this);
		}
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bloc);
		 ((BlocContext)_localctx).code =  "";
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(T__22);
			setState(227); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(224);
				((BlocContext)_localctx).instruction = instruction();
				 _localctx.code += ((BlocContext)_localctx).instruction.code;
				}
				}
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__22) | (1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << NEWLINE) | (1L << ENTIER) | (1L << RETURN) | (1L << IDENTIFIANT))) != 0) );
			setState(231);
			match(T__23);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(232);
					match(NEWLINE);
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeifContext extends ParserRuleContext {
		public String code;
		public ConditionContext a;
		public InstructionContext b;
		public InstructionContext c;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CodeifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCodeif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCodeif(this);
		}
	}

	public final CodeifContext codeif() throws RecognitionException {
		CodeifContext _localctx = new CodeifContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_codeif);
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				match(T__24);
				setState(239);
				match(T__0);
				setState(240);
				((CodeifContext)_localctx).a = condition(0);
				setState(241);
				match(T__1);
				setState(242);
				((CodeifContext)_localctx).b = instruction();


				        ((CodeifContext)_localctx).code =  evalif(((CodeifContext)_localctx).a.code, ((CodeifContext)_localctx).b.code);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(T__24);
				setState(246);
				match(T__0);
				setState(247);
				((CodeifContext)_localctx).a = condition(0);
				setState(248);
				match(T__1);
				setState(249);
				((CodeifContext)_localctx).b = instruction();
				setState(250);
				match(T__25);
				setState(251);
				((CodeifContext)_localctx).c = instruction();

				        ((CodeifContext)_localctx).code =  evalifelse(((CodeifContext)_localctx).a.code, ((CodeifContext)_localctx).b.code, ((CodeifContext)_localctx).c.code);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeforContext extends ParserRuleContext {
		public String code;
		public AssignationContext a;
		public ConditionContext c;
		public AssignationContext b;
		public InstructionContext i;
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public CodeforContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codefor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCodefor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCodefor(this);
		}
	}

	public final CodeforContext codefor() throws RecognitionException {
		CodeforContext _localctx = new CodeforContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_codefor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__26);
			setState(257);
			match(T__0);
			setState(258);
			((CodeforContext)_localctx).a = assignation();
			setState(259);
			match(T__6);
			setState(260);
			((CodeforContext)_localctx).c = condition(0);
			setState(261);
			match(T__6);
			setState(262);
			((CodeforContext)_localctx).b = assignation();
			setState(263);
			match(T__1);
			setState(264);
			((CodeforContext)_localctx).i = instruction();

			        ((CodeforContext)_localctx).code =  evalfor(((CodeforContext)_localctx).a.code, ((CodeforContext)_localctx).c.code, ((CodeforContext)_localctx).b.code, ((CodeforContext)_localctx).i.code);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CoderepeatContext extends ParserRuleContext {
		public String code;
		public InstructionContext i;
		public ConditionContext c;
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CoderepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coderepeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCoderepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCoderepeat(this);
		}
	}

	public final CoderepeatContext coderepeat() throws RecognitionException {
		CoderepeatContext _localctx = new CoderepeatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_coderepeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(T__27);
			setState(268);
			((CoderepeatContext)_localctx).i = instruction();
			setState(269);
			match(T__28);
			setState(270);
			match(T__0);
			setState(271);
			((CoderepeatContext)_localctx).c = condition(0);
			setState(272);
			match(T__1);

			        ((CoderepeatContext)_localctx).code =  evalRepeat(((CoderepeatContext)_localctx).i.code, ((CoderepeatContext)_localctx).c.code);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public BlocContext bloc;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fonction);
		 tablesSymboles.newTableLocale(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			((FonctionContext)_localctx).TYPE = match(TYPE);

			            tablesSymboles.putVar("return", (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			            // code java pour gérer la déclaration de "la variable" de retour de la fonction
			        
			setState(277);
			((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(278);
			match(T__0);
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(279);
				params();
				}
			}

			setState(282);
			match(T__1);

			            ((FonctionContext)_localctx).code =  "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
			            tablesSymboles.newFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null), (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			        
			setState(284);
			((FonctionContext)_localctx).bloc = bloc();

			            _localctx.code += ((FonctionContext)_localctx).bloc.code;
			            _localctx.code += "RETURN\n";
			        
			}
			_ctx.stop = _input.LT(-1);
			 tablesSymboles.dropTableLocale(); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIANT;
		public List<TerminalNode> TYPE() { return getTokens(CalculetteParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CalculetteParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIANT() { return getTokens(CalculetteParser.IDENTIFIANT); }
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(CalculetteParser.IDENTIFIANT, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			((ParamsContext)_localctx).TYPE = match(TYPE);
			setState(288);
			((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

			            tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
			            // code java gérant la déclaration de "la variable" de retour de la fonction
			        
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__29) {
				{
				{
				setState(290);
				match(T__29);
				setState(291);
				((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(292);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				                tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
				                // code java gérant une variable locale (argi)
				            
				}
				}
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << ENTIER) | (1L << IDENTIFIANT))) != 0)) {
				{
				setState(299);
				((ArgsContext)_localctx).expression = expression(0);

				        ((ArgsContext)_localctx).code =  ((ArgsContext)_localctx).expression.code;
				        ((ArgsContext)_localctx).size =  AdresseType.getSize(((ArgsContext)_localctx).expression.type);
				        // code java pour première expression pour arg
				    
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__29) {
					{
					{
					setState(301);
					match(T__29);
					setState(302);
					((ArgsContext)_localctx).expression = expression(0);

					        _localctx.code += ((ArgsContext)_localctx).expression.code;
					        _localctx.size += AdresseType.getSize(((ArgsContext)_localctx).expression.type);
					        // code java pour expression suivante pour arg
					    
					}
					}
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 9:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u013b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\7\3\62\n\3\f"+
		"\3\16\3\65\13\3\3\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\3\7\3@\n\3\f\3\16"+
		"\3C\13\3\3\3\3\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4s\n\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5\u0088\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0094\n\5"+
		"\f\5\16\5\u0097\13\5\3\6\6\6\u009a\n\6\r\6\16\6\u009b\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00aa\n\7\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ba\n\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00d1\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13\u00dd\n\13\f\13\16\13\u00e0\13\13\3\f\3\f\3\f\3\f\6\f\u00e6\n\f"+
		"\r\f\16\f\u00e7\3\f\3\f\7\f\u00ec\n\f\f\f\16\f\u00ef\13\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0101\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\5\20\u011b\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0129\n\21\f\21"+
		"\16\21\u012c\13\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0134\n\22\f\22"+
		"\16\22\u0137\13\22\5\22\u0139\n\22\3\22\2\4\b\24\23\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"\2\6\3\2\5\6\3\2\7\b\4\2\t\t!!\3\2\21\26\2\u014c"+
		"\2$\3\2\2\2\4,\3\2\2\2\6r\3\2\2\2\b\u0087\3\2\2\2\n\u0099\3\2\2\2\f\u00a9"+
		"\3\2\2\2\16\u00ab\3\2\2\2\20\u00b9\3\2\2\2\22\u00bb\3\2\2\2\24\u00d0\3"+
		"\2\2\2\26\u00e1\3\2\2\2\30\u0100\3\2\2\2\32\u0102\3\2\2\2\34\u010d\3\2"+
		"\2\2\36\u0115\3\2\2\2 \u0121\3\2\2\2\"\u0138\3\2\2\2$%\5\4\3\2%&\7\2\2"+
		"\3&\3\3\2\2\2\'(\5\f\7\2()\b\3\1\2)+\3\2\2\2*\'\3\2\2\2+.\3\2\2\2,*\3"+
		"\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\63\b\3\1\2\60\62\7!\2\2\61\60\3"+
		"\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64;\3\2\2\2\65\63\3\2"+
		"\2\2\66\67\5\36\20\2\678\b\3\1\28:\3\2\2\29\66\3\2\2\2:=\3\2\2\2;9\3\2"+
		"\2\2;<\3\2\2\2<A\3\2\2\2=;\3\2\2\2>@\7!\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2"+
		"\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DJ\b\3\1\2EF\5\6\4\2FG\b\3\1\2GI\3\2"+
		"\2\2HE\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\b\3"+
		"\1\2N\5\3\2\2\2OP\5\26\f\2PQ\b\4\1\2Qs\3\2\2\2RS\5\b\5\2ST\5\n\6\2TU\b"+
		"\4\1\2Us\3\2\2\2VW\5\16\b\2WX\5\n\6\2XY\b\4\1\2Ys\3\2\2\2Z[\5\20\t\2["+
		"\\\5\n\6\2\\]\b\4\1\2]s\3\2\2\2^_\5\22\n\2_`\b\4\1\2`s\3\2\2\2ab\5\30"+
		"\r\2bc\b\4\1\2cs\3\2\2\2de\5\32\16\2ef\b\4\1\2fs\3\2\2\2gh\5\34\17\2h"+
		"i\b\4\1\2is\3\2\2\2jk\7%\2\2kl\5\b\5\2lm\5\n\6\2mn\b\4\1\2ns\3\2\2\2o"+
		"p\5\n\6\2pq\b\4\1\2qs\3\2\2\2rO\3\2\2\2rR\3\2\2\2rV\3\2\2\2rZ\3\2\2\2"+
		"r^\3\2\2\2ra\3\2\2\2rd\3\2\2\2rg\3\2\2\2rj\3\2\2\2ro\3\2\2\2s\7\3\2\2"+
		"\2tu\b\5\1\2uv\7\3\2\2vw\5\b\5\2wx\7\4\2\2xy\b\5\1\2y\u0088\3\2\2\2z{"+
		"\7#\2\2{\u0088\b\5\1\2|}\7\b\2\2}~\7#\2\2~\u0088\b\5\1\2\177\u0080\7&"+
		"\2\2\u0080\u0081\7\3\2\2\u0081\u0082\5\"\22\2\u0082\u0083\7\4\2\2\u0083"+
		"\u0084\b\5\1\2\u0084\u0088\3\2\2\2\u0085\u0086\7&\2\2\u0086\u0088\b\5"+
		"\1\2\u0087t\3\2\2\2\u0087z\3\2\2\2\u0087|\3\2\2\2\u0087\177\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0095\3\2\2\2\u0089\u008a\f\b\2\2\u008a\u008b\t\2"+
		"\2\2\u008b\u008c\5\b\5\t\u008c\u008d\b\5\1\2\u008d\u0094\3\2\2\2\u008e"+
		"\u008f\f\7\2\2\u008f\u0090\t\3\2\2\u0090\u0091\5\b\5\b\u0091\u0092\b\5"+
		"\1\2\u0092\u0094\3\2\2\2\u0093\u0089\3\2\2\2\u0093\u008e\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\t\3\2\2\2"+
		"\u0097\u0095\3\2\2\2\u0098\u009a\t\4\2\2\u0099\u0098\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\13\3\2\2\2\u009d"+
		"\u009e\7$\2\2\u009e\u009f\7&\2\2\u009f\u00a0\7\n\2\2\u00a0\u00a1\7#\2"+
		"\2\u00a1\u00a2\5\n\6\2\u00a2\u00a3\b\7\1\2\u00a3\u00aa\3\2\2\2\u00a4\u00a5"+
		"\7$\2\2\u00a5\u00a6\7&\2\2\u00a6\u00a7\5\n\6\2\u00a7\u00a8\b\7\1\2\u00a8"+
		"\u00aa\3\2\2\2\u00a9\u009d\3\2\2\2\u00a9\u00a4\3\2\2\2\u00aa\r\3\2\2\2"+
		"\u00ab\u00ac\7&\2\2\u00ac\u00ad\7\n\2\2\u00ad\u00ae\5\b\5\2\u00ae\u00af"+
		"\b\b\1\2\u00af\17\3\2\2\2\u00b0\u00b1\7\13\2\2\u00b1\u00b2\7&\2\2\u00b2"+
		"\u00b3\7\4\2\2\u00b3\u00ba\b\t\1\2\u00b4\u00b5\7\f\2\2\u00b5\u00b6\5\b"+
		"\5\2\u00b6\u00b7\7\4\2\2\u00b7\u00b8\b\t\1\2\u00b8\u00ba\3\2\2\2\u00b9"+
		"\u00b0\3\2\2\2\u00b9\u00b4\3\2\2\2\u00ba\21\3\2\2\2\u00bb\u00bc\7\r\2"+
		"\2\u00bc\u00bd\7\3\2\2\u00bd\u00be\5\24\13\2\u00be\u00bf\7\4\2\2\u00bf"+
		"\u00c0\5\6\4\2\u00c0\u00c1\b\n\1\2\u00c1\23\3\2\2\2\u00c2\u00c3\b\13\1"+
		"\2\u00c3\u00c4\7\20\2\2\u00c4\u00c5\5\24\13\6\u00c5\u00c6\b\13\1\2\u00c6"+
		"\u00d1\3\2\2\2\u00c7\u00c8\5\b\5\2\u00c8\u00c9\t\5\2\2\u00c9\u00ca\5\b"+
		"\5\2\u00ca\u00cb\b\13\1\2\u00cb\u00d1\3\2\2\2\u00cc\u00cd\7\27\2\2\u00cd"+
		"\u00d1\b\13\1\2\u00ce\u00cf\7\30\2\2\u00cf\u00d1\b\13\1\2\u00d0\u00c2"+
		"\3\2\2\2\u00d0\u00c7\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1"+
		"\u00de\3\2\2\2\u00d2\u00d3\f\b\2\2\u00d3\u00d4\7\16\2\2\u00d4\u00d5\5"+
		"\24\13\t\u00d5\u00d6\b\13\1\2\u00d6\u00dd\3\2\2\2\u00d7\u00d8\f\7\2\2"+
		"\u00d8\u00d9\7\17\2\2\u00d9\u00da\5\24\13\b\u00da\u00db\b\13\1\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u00d2\3\2\2\2\u00dc\u00d7\3\2\2\2\u00dd\u00e0\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\25\3\2\2\2\u00e0\u00de"+
		"\3\2\2\2\u00e1\u00e5\7\31\2\2\u00e2\u00e3\5\6\4\2\u00e3\u00e4\b\f\1\2"+
		"\u00e4\u00e6\3\2\2\2\u00e5\u00e2\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e5"+
		"\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ed\7\32\2\2"+
		"\u00ea\u00ec\7!\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\27\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00f1\7\33\2\2\u00f1\u00f2\7\3\2\2\u00f2\u00f3\5\24\13\2\u00f3\u00f4"+
		"\7\4\2\2\u00f4\u00f5\5\6\4\2\u00f5\u00f6\b\r\1\2\u00f6\u0101\3\2\2\2\u00f7"+
		"\u00f8\7\33\2\2\u00f8\u00f9\7\3\2\2\u00f9\u00fa\5\24\13\2\u00fa\u00fb"+
		"\7\4\2\2\u00fb\u00fc\5\6\4\2\u00fc\u00fd\7\34\2\2\u00fd\u00fe\5\6\4\2"+
		"\u00fe\u00ff\b\r\1\2\u00ff\u0101\3\2\2\2\u0100\u00f0\3\2\2\2\u0100\u00f7"+
		"\3\2\2\2\u0101\31\3\2\2\2\u0102\u0103\7\35\2\2\u0103\u0104\7\3\2\2\u0104"+
		"\u0105\5\16\b\2\u0105\u0106\7\t\2\2\u0106\u0107\5\24\13\2\u0107\u0108"+
		"\7\t\2\2\u0108\u0109\5\16\b\2\u0109\u010a\7\4\2\2\u010a\u010b\5\6\4\2"+
		"\u010b\u010c\b\16\1\2\u010c\33\3\2\2\2\u010d\u010e\7\36\2\2\u010e\u010f"+
		"\5\6\4\2\u010f\u0110\7\37\2\2\u0110\u0111\7\3\2\2\u0111\u0112\5\24\13"+
		"\2\u0112\u0113\7\4\2\2\u0113\u0114\b\17\1\2\u0114\35\3\2\2\2\u0115\u0116"+
		"\7$\2\2\u0116\u0117\b\20\1\2\u0117\u0118\7&\2\2\u0118\u011a\7\3\2\2\u0119"+
		"\u011b\5 \21\2\u011a\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2"+
		"\2\2\u011c\u011d\7\4\2\2\u011d\u011e\b\20\1\2\u011e\u011f\5\26\f\2\u011f"+
		"\u0120\b\20\1\2\u0120\37\3\2\2\2\u0121\u0122\7$\2\2\u0122\u0123\7&\2\2"+
		"\u0123\u012a\b\21\1\2\u0124\u0125\7 \2\2\u0125\u0126\7$\2\2\u0126\u0127"+
		"\7&\2\2\u0127\u0129\b\21\1\2\u0128\u0124\3\2\2\2\u0129\u012c\3\2\2\2\u012a"+
		"\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b!\3\2\2\2\u012c\u012a\3\2\2\2"+
		"\u012d\u012e\5\b\5\2\u012e\u0135\b\22\1\2\u012f\u0130\7 \2\2\u0130\u0131"+
		"\5\b\5\2\u0131\u0132\b\22\1\2\u0132\u0134\3\2\2\2\u0133\u012f\3\2\2\2"+
		"\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0139"+
		"\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u012d\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"#\3\2\2\2\30,\63;AJr\u0087\u0093\u0095\u009b\u00a9\u00b9\u00d0\u00dc\u00de"+
		"\u00e7\u00ed\u0100\u011a\u012a\u0135\u0138";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}