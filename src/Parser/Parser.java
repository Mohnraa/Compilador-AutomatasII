package Parser;

import Errores.UnexpectedTokenException;
import java.util.LinkedList;

public class Parser {
    final int If = 1, Then = 2, Else = 3,
    Begin = 4, End = 5, Print = 6, Semi = 7,
    num = 8, EQ = 9, Int = 10, Float = 11, Digito = 12;
    int tokenCode, numLinea;
    LinkedList<LinkedList<String>> tokens;
    LinkedList<String> linea;
    String token;
    
    public Parser(LinkedList<LinkedList<String>> tokens){
        this.tokens = tokens;
    }
    
    public void comenzarParser() throws UnexpectedTokenException{
        numLinea = 0;
        avanza();
        S();
        System.out.print("Parsing completado con exito");
    }
    
    private void avanza(){
        if(token == null){
            nextLine();
        }
        token = linea.pollFirst();
        if(token == null){
            return;
        }
        tokenCode = stringToCode(token);
    }
    
    private void come(int tok) throws UnexpectedTokenException{
        if (tokenCode == tok){
            avanza();
        }
        else{
            throw new UnexpectedTokenException("Token inesperado en la linea "+ numLinea + ".\nCodigo de token esperado: "+ tokenCode + ".\nToken encontrado: " + token);
        }
    }
    
    private void nextLine(){
        if (tokens.isEmpty()){
            return;
        }
	linea = tokens.removeFirst();
        numLinea++;
    }
    
    private int stringToCode(String cad){
        int cod;
        switch(cad){
            case "If":
                cod = 1;
                break;
            case "Then":
                cod = 2;
                break;
            case "Else":
                cod = 3;
                break;
            case "Begin":
                cod = 4;
                break;
            case "End":
                cod = 5;
                break;
            case "Print":
                cod = 6;
                break;
            case "Semi":
                cod = 7;
                break;
            case "num":
                cod = 8;
                break;
            case "=":
                cod = 9;
                break;
            case "Int":
                cod = 10;
                break;
            case "Float":
                cod = 11;
                break;
            default:
                // Caso default para numeros
                if (cad.matches("\\d+")){
                    cod = 12;
                }
                // Caso default para errores
                else{
                    cod = 13;
                }     
        }
        return cod;
    }
    
    private void S() throws UnexpectedTokenException{
        switch(stringToCode(token)){
            case If:
                come(If);
                E();
                come(Then);
                S();
                come(Else);
                S();
                break;
            case Begin:
                come(Begin);
                S();
                L();
                break;
            case Print:
                come(Print);
                E();
                break;
            case Int:
                come(Int);
                come(Digito);
                break;
            case Float:
                come(Float);
                come(Digito);
                break;
            default:
                throw new UnexpectedTokenException("Token inesperado en la linea "+ numLinea + ".\nCodigo de token esperado: "+ tokenCode + ".\nToken encontrado: " + token);
        }
    }
    
    private void L() throws UnexpectedTokenException{
        switch(stringToCode(token)){
            case End:
                come(End);
                break;
            case Semi:
                come(Semi);
                S();
                L();
                break;
            default:
                throw new UnexpectedTokenException("Token inesperado en la linea "+ numLinea + ".\nCodigo de token esperado: "+ tokenCode + ".\nToken encontrado: " + token);
        }
    }
    
    private void E() throws UnexpectedTokenException{
        switch(stringToCode(token)){
            case Digito:
                come(Digito);
                come(EQ);
                come(Digito);
                break;
            default:
                throw new UnexpectedTokenException("Token inesperado en la linea "+ numLinea + ".\nCodigo de token esperado: "+ tokenCode + ".\nToken encontrado: " + token);
        }
    }
}
