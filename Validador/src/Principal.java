
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.*;  
import javax.xml.transform.stream.*;  
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class Principal {

    public static void main(String[] args) {
//        validarXMLvsXSD();
//        manipularXML();
        transformaXMLvsXSL();
//        manipularXMLJDom();
//        criandoMeuXMLNoJava();

    }
    
    private static void validarXMLvsXSD() {
        boolean isValidada = validate("XML_XSD" + File.separator + "arquivoXSD.xsd", "XML_XSD" + File.separator + "arquivoXML.xml");
//        boolean isValidada = validate("XML_XSD" + File.separator + "dadosPessoaisValidate.xsd", "XML_XSD" + File.separator + "dadosPessoais.xml");
//        boolean isValidada = validate("XML_XSD" + File.separator + "enviNFe_v3.10.xsd", "XML_XSD" + File.separator + "notaExemplo.xml");
        String status = ((isValidada) ? "correto!" : "incorreto!");

        System.out.println("O retorno da validação do XML de dadosPessoais.xml com o XSD de validação dadosPessoaisValidate.xsd está " + status);
    }

    private static boolean validate(String pathXSD, String pathXML) {
        try {
            File xsdFile = new File(pathXSD);
            File xmlFile = new File(pathXML);

            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(xmlFile);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Source schemaFile = new StreamSource(xsdFile);
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();

            validator.validate(new DOMSource(document));
        } catch (Exception e) {
            System.out.println("Ocorreu o erro:\n" + e.getMessage());
            return false;
        }

        return true;
    }

    private static void manipularXML() {
        try {
            File xmlFile = new File("XML_XSD" + File.separator + "dadosPessoais.xml");
            File xsdFile = new File("XML_XSD" + File.separator + "dadosPessoaisValidate.xsd");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder = dbf.newDocumentBuilder();

            Document doc = docBuilder.parse(xmlFile);

            Element dadosPessoais = doc.getDocumentElement();

            Element nome = (Element) dadosPessoais.getElementsByTagName("nome").item(0);
            
            System.out.println("O valor do elemento nome é: " + nome.getTextContent());

        } catch (Exception e) {
            System.out.println("Ocorreu o erro:\n" + e.getMessage());

        }

    }

    public static void transformaXMLvsXSL() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();  
            Transformer transformer = factory.newTransformer(new StreamSource("XML_XSD" + File.separator + "dadosPessoaisXSL.xsl"));  
              
            transformer.transform(new StreamSource("XML_XSD" + File.separator + "dadosPessoaisDATAXMLXSL.xml"), new StreamResult(new FileOutputStream("XML_XSD" + File.separator + "dadosPessoaisXSL.html")));  
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void manipularXMLJDom() {
        try {
            File xmlFile = new File("XML_XSD" + File.separator + "dadosPessoais.xml");
            
            SAXBuilder saxBuilder = new SAXBuilder();

            org.jdom2.Document document = saxBuilder.build(xmlFile);
            
            org.jdom2.Element dadosPessoais = document.getRootElement();
            
            // Outra forma de estar criando o Elements do XML
//            org.jdom2.Element dadosPessoais = new Element("dadosPessoais");

            // Criação do Namespace em Java para atribuir ao XML Schema do mesmo
            Namespace xsd = Namespace.getNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
            dadosPessoais.addNamespaceDeclaration(xsd);
            
            org.jdom2.Element nome = dadosPessoais.getChild("nome");
            
            System.out.println("O valor do elemento nome é: " + nome.getText());

        } catch (Exception e) {
            System.out.println("Ocorreu o erro:\n" + e.getMessage());

        }
    }
    
    private static void criandoMeuXMLNoJava() {
        /*
        <mural>  
            <mensagem id="" prioridade="">    
                <para></para>  
                <de></de>  
                <corpo></corpo>  
            </mensagem>  
        </mural> 
        */
        // Declaração dos elementos que irão compor a estrutura do XML  
        org.jdom2.Element mural = new org.jdom2.Element("mural");  
        org.jdom2.Element mensagem = new org.jdom2.Element("mensagem");  
        org.jdom2.Element para = new org.jdom2.Element("para");  
        org.jdom2.Element de = new org.jdom2.Element("de");  
        org.jdom2.Element corpo = new org.jdom2.Element("corpo");  

        // "Setando" os atributos  
        mensagem.setAttribute("id", "01");  

        //"Setando" outro atributo agora utilizando da classe Attribute  
        Attribute prioridade = new Attribute("prioridade","-1");  
        mensagem.setAttribute(prioridade);    

        mensagem.addContent(para);  
        mensagem.addContent(de);  
        mensagem.addContent(corpo);  

        mural.addContent(mensagem);
        
        de.setText("robsonburatti@gmail.com");
        para.setText("la24s.4si@alunos.utfpr.edu.br");
        corpo.setText("Olá! Estudem e pratiquem os exercícios para a avaliação. Bons Estudos!");

        //Criando o documento XML (montado)  
        org.jdom2.Document doc = new org.jdom2.Document();  
        doc.setRootElement(mural);  

        //Imptrimindo o XML  
        XMLOutputter xout = new XMLOutputter();  
        
        try {
            OutputStream out = new FileOutputStream(new File("XML_XSD" + File.separator + "meuXMLCriadoNoJava.xml"));
            xout.setFormat(Format.getPrettyFormat());
            xout.output(doc, out);
            xout.output(doc, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
