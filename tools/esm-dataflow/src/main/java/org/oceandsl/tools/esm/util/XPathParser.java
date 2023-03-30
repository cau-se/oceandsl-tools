package org.oceandsl.tools.esm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.oceandsl.tools.fxca.tools.NodePredicateUtils;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;

public class XPathParser {

    /**
     * Return list of bodies of subroutines
     *
     * @param document
     * @return return list of subroutine bodies
     */
    public static List<List<Node>> getSubroutineContents(final Document document) {
        final List<List<Node>> subRs = new ArrayList<>();

        final NodeList nList = document.getElementsByTagName("subroutine-stmt");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            final List<Node> currentNList = new ArrayList<>();
            currentNList.add(node);
            while ((node.getNextSibling() != null)
                    && !node.getNextSibling().getNodeName().equals("end-subroutine-stmt")) {
                currentNList.add(node.getNextSibling());
                node = node.getNextSibling();
            }
            subRs.add(currentNList);

        }

        return subRs;

    }

    /**
     * Return list of bodies of functions
     *
     * @param xml
     * @return return list of functions bodies
     */
    public static List<List<Node>> getFuncContents(final Document document) {
        final List<List<Node>> subRs = new ArrayList<>();
        final NodeList nList = document.getElementsByTagName("function-stmt");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            final List<Node> currentNList = new ArrayList<>();
            currentNList.add(node);
            while ((node.getNextSibling() != null) && node.getNextSibling().getNodeName().equals("end-function-stmt")) {
                currentNList.add(node.getNextSibling());
                node = node.getNextSibling();
            }
            subRs.add(currentNList);

        }

        return subRs;
    }

    public static Set<Node> getMain(final Document document) {
        return NodeProcessingUtils.findAllSiblings(document.getFirstChild(), NodePredicateUtils.isProgramStatement,
                NodePredicateUtils.isEndProgramStatement);
    }

    public static Set<Node> getCallStmts(final Node body) {
        return NodeProcessingUtils.findAllSiblings(body.getFirstChild(), NodePredicateUtils.isCallStatement,
                NodePredicateUtils.isEndSubroutineStatement);
    }

    public static Set<Node> getCommonBlocks(final Node body) {
        return NodeProcessingUtils.findAllSiblings(body.getFirstChild(), NodePredicateUtils.isCommonStatement,
                o -> false);
    }

    public static Set<Node> getIfElseStmts(final Node body) {
        return NodeProcessingUtils.findAllSiblings(body.getFirstChild(), NodePredicateUtils.isIfThenStatement,
                NodePredicateUtils.isEndIfStatement);
    }

    public static Set<Node> getSelectStmts(final Node body) {
        return NodeProcessingUtils.findAllSiblings(body.getFirstChild(), NodePredicateUtils.isSelectCaseStatement,
                o -> false);
    }

    public static Set<Node> getLoopCtrlStmts(final Node body) {
        return NodeProcessingUtils.findAllSiblings(body.getFirstChild(), NodePredicateUtils.isDoStatement, o -> false);
    }

    public static String getLoopControlVar(final Node loopStatement) {
        String result = null;
        final Element e = (Element) loopStatement;
        final NodeList ctrlVar = e.getElementsByTagName("do-V");
        if (ctrlVar.getLength() > 0) {
            final Element nE = (Element) ctrlVar.item(0);
            result = nE.getElementsByTagName("n").item(0).getTextContent();
        }

        return result;
    }

    public static Set<Node> getAssignmentStmts(final Node body) {
        return NodeProcessingUtils.findAllSiblings(body.getFirstChild(), NodePredicateUtils.isAssignmentStatement,
                o -> false);
    }

    public static List<String> callHasArgs(final Node callStmt) {
        final List<String> result = new ArrayList<>();
        if (callStmt instanceof Element) {
            final Element e = (Element) callStmt;
            final NodeList args = e.getElementsByTagName("arg");

            for (int i = 0; i < args.getLength(); i++) {
                final Element arg = (Element) args.item(i);
                // System.out.println(arg.getElementsByTagName("n").getLength());
                final NodeList ns = arg.getElementsByTagName("n");
                for (int j = 0; j < ns.getLength(); j++) {
                    final Element n = (Element) ns.item(j);
                    // System.out.println(n.getTextContent());
                    result.add(n.getTextContent());
                }
            }
        }
        return result;
    }

    public static List<String> getNamesFromStatement(final Node stmt) {
        final List<String> result = new ArrayList<>();
        final Element e = (Element) stmt;
        final NodeList nameElems = e.getElementsByTagName("n");

        for (int i = 0; i < nameElems.getLength(); i++) {
            result.add(nameElems.item(i).getTextContent());
        }

        return result;
    }

    /**
     * Get variable name frome left part of an assignment as a string
     *
     * @param aStmt
     * @return
     */
    public static String getAssignTargetIdentifier(final Node left) {
        final List<String> result = new ArrayList<>();
        final Element e = (Element) left;
        final NodeList nameElems = e.getElementsByTagName("n");

        for (int i = 0; i < nameElems.getLength(); i++) {
            result.add(nameElems.item(i).getTextContent());
        }

        return result.get(0);
    }

    public static String getStructureConstructorIdentifier(final Node cons) {
        final List<String> result = new ArrayList<>();
        final Element e = (Element) cons;
        final NodeList nameElems = e.getElementsByTagName("n");

        for (int i = 0; i < nameElems.getLength(); i++) {
            result.add(nameElems.item(i).getTextContent());
        }

        return result.get(0);
    }

    public static String getPartRefNodeIdentifier(final Node partRef) {
        final List<String> result = new ArrayList<>();
        final Element e = (Element) partRef;
        final NodeList nameElems = e.getElementsByTagName("n");

        for (int i = 0; i < nameElems.getLength(); i++) {
            result.add(nameElems.item(i).getTextContent());
        }

        return result.get(0);
    }

    public static List<String> getArgumentList(final Node procedure) {
        final List<String> result = new ArrayList<>();
        final Element e = (Element) procedure;
        final NodeList nameElems = e.getElementsByTagName("element");

        for (int i = 0; i < nameElems.getLength(); i++) {
            result.add(nameElems.item(i).getTextContent());
        }

        return result;
    }

    public static String getSubroutineId(final List<Node> body) {
        final Element e = (Element) body.get(0);
        final NodeList nameElems = e.getElementsByTagName("n");

        return nameElems.item(0).getTextContent();
    }

    public static String getFunctionId(final List<Node> body) {
        final Element e = (Element) body.get(0);
        final NodeList nameElems = e.getElementsByTagName("n");
        return nameElems.item(0).getTextContent();
    }

    public static String getCallStmtId(final Node callStmt) {
        final Element e = (Element) callStmt;
        final NodeList nameElems = e.getElementsByTagName("n");
        return nameElems.item(0).getTextContent();
    }

    public static String getCommonBlockId(final Node commonBlock) {
        final Element e = (Element) commonBlock;
        final NodeList nameElems = e.getElementsByTagName("n");

        return nameElems.item(0).getTextContent();
    }

    public static List<Node> getPotentialFuncs(final Node assigningContent) {
        final List<Node> result = new ArrayList<>();
        final Element e = (Element) assigningContent;
        // check if named expressions exist
        final NodeList nameExpr = e.getElementsByTagName("named-E");

        if (nameExpr.getLength() > 0) {
            for (int i = 0; i < nameExpr.getLength(); i++) {
                // check if parenthesis exist
                final Element currentNode = (Element) nameExpr.item(i);
                final NodeList parensElems = currentNode.getElementsByTagName("parens-R");
                if (parensElems.getLength() > 0) {
                    // check if arguments exists
                    for (int j = 0; j < parensElems.getLength(); j++) {
                        parensElems.item(j);
                        final NodeList elemElems = currentNode.getElementsByTagName("element");
                        if (elemElems.getLength() > 0) {
                            // all checls passed! Add the named expression to the list!
                            result.add(currentNode);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static List<Node> getNonArgsFunc(final Node assigningContent) {
        final List<Node> result = new ArrayList<>();
        final Element e = (Element) assigningContent;
        // check if named expressions exist
        final NodeList nameExpr = e.getElementsByTagName("named-E");

        if (nameExpr.getLength() > 0) {
            for (int i = 0; i < nameExpr.getLength(); i++) {
                // check if parenthesis exist
                final Element currentNode = (Element) nameExpr.item(i);
                final NodeList parensElems = currentNode.getElementsByTagName("parens-R");
                if (parensElems.getLength() > 0) {
                    // check if arguments exists
                    for (int j = 0; j < parensElems.getLength(); j++) {
                        parensElems.item(j);
                        final NodeList elemElems = currentNode.getElementsByTagName("element");
                        if (elemElems.getLength() == 0) {
                            // parenthesis but no args. Add
                            // all checls passed! Add the named expression to the list!
                            result.add(currentNode);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static List<Node> getNames(final Node assigningContent) {
        final List<Node> result = new ArrayList<>();
        final Element e = (Element) assigningContent;
        final NodeList nameElems = e.getElementsByTagName("n");

        for (int i = 0; i < nameElems.getLength(); i++) {
            result.add(nameElems.item(i));
        }

        return result;
    }

    public static List<String> getArraysDecl(final List<List<Node>> bodies) {
        final List<String> result = new ArrayList<>();

        for (final List<Node> body : bodies) { // iterate through bodies
            for (final Node node : body) { // get a body
                if (node.getNodeName().equals("T-decl-stmt")) {// find decl stmt
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        final Element elem = (Element) node;
                        final NodeList elems = elem.getElementsByTagName("EN-decl"); // get entity
                                                                                     // decl
                        for (int i = 0; i < elems.getLength(); i++) {// iterate
                            final Element endecl = (Element) elems.item(i);
                            final NodeList a_specs = endecl.getElementsByTagName("array-spec"); // is
                                                                                                // array?
                            if (a_specs.getLength() > 0) {
                                result.add(endecl.getElementsByTagName("n").item(0).getTextContent()); // blacklist!!
                            }
                        }
                    }

                }
            }
        }
        return result;
    }

    // -----------------------
    public static Node getAssigningContent(final Node stmt) { // right
        final Element e = (Element) stmt;
        final NodeList elems = e.getElementsByTagName("E-2");
        return elems.item(0);
    }

    public static Node getAssignedContent(final Node stmt) { // left
        final Element e = (Element) stmt;
        final NodeList elems = e.getElementsByTagName("E-1");
        return elems.item(0);
    }
    // ------------------------------------

    public static List<String> getCommonVars(final Node commonBlock) {
        final List<String> result = new ArrayList<>();
        final Element commonBl = (Element) commonBlock;// cast
        final Element commonLt = (Element) commonBl.getElementsByTagName("common-block-obj-LT").item(0);// lt
        final NodeList names = commonLt.getElementsByTagName("n");// now names
        for (int i = 0; i < names.getLength(); i++) {
            result.add(names.item(i).getTextContent());
        }
        return result;
    }

}
