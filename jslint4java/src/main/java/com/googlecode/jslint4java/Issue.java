package com.googlecode.jslint4java;

import org.mozilla.javascript.Scriptable;

/**
 * A single issue with the code that is being checked for problems.
 * 
 * @author dom
 * @version $Id$
 */
public class Issue {

    /**
     * Allow creating an issue in a couple of different ways.
     */
    public static class IssueBuilder {

        /** Build from a JavaScript context */
        public static Issue fromJavaScript(String systemId, Scriptable err) {
            // jslint emits zero based lines & columns.
            int line = Util.intValue("line", err) + 1;
            int col = Util.intValue("character", err) + 1;
            return new IssueBuilder(systemId, line, col, Util.stringValue("reason", err))
                    .evidence(Util.stringValue("evidence", err))
                    .raw(Util.stringValue("raw", err))
                    .a(Util.stringValue("a", err))
                    .b(Util.stringValue("b", err))
                    .c(Util.stringValue("c", err))
                    .d(Util.stringValue("d", err))
                    .build();
        }

        private String a;
        private String b;
        private String c;
        private int character;
        private String d;
        private String evidence;
        private int line;
        private String raw;
        private String reason;
        private String systemId;

        public IssueBuilder(String systemId, int line, int character, String reason) {
            this.character = character;
            this.line = line;
            this.reason = reason;
            this.systemId = systemId;
        }

        public Issue build() {
            return new Issue(this);
        }

        public IssueBuilder a(String a) {
            this.a = a;
            return this;
        }

        public IssueBuilder b(String b) {
            this.b = b;
            return this;
        }

        public IssueBuilder c(String c) {
            this.c = c;
            return this;
        }

        public IssueBuilder d(String d) {
            this.d = d;
            return this;
        }

        public IssueBuilder evidence(String evidence) {
            this.evidence = evidence;
            return this;
        }

        public IssueBuilder raw(String raw) {
            this.raw = raw;
            return this;
        }
    }
    private String a;
    private String b;
    private String c;
    private int character;
    private String d;
    private String evidence;
    private int line;
    private String raw;
    private String reason;
    private String systemId;

    private Issue(IssueBuilder ib) {
        this.systemId = ib.systemId;
        this.reason = ib.reason;
        this.line = ib.line;
        this.character = ib.character;
        this.evidence = ib.evidence;
        this.raw = ib.raw;
        this.a = ib.a;
        this.b = ib.b;
        this.c = ib.c;
        this.d = ib.d;
    }

    /**
     * @return A string of auxiliary information.
     */
    public String getA() {
        return a;
    }

    /**
     * @return A string of auxiliary information.
     */
    public String getB() {
        return b;
    }

    /**
     * @return A string of auxiliary information.
     */
    public String getC() {
        return c;
    }

    /**
     * @return the position of the issue within the line. Starts at 0.
     */
    public int getCharacter() {
        return character;
    }

    /**
     * @return a string of auxiliary information.
     */
    public String getD() {
        return d;
    }

    /**
     * @return the contents of the line in which this issue occurs.
     */
    public String getEvidence() {
        return evidence;
    }

    /**
     * @return the number of the line on which this issue occurs.
     */
    public int getLine() {
        return line;
    }

    /**
     * @return a copy of the evidence, but before substitution has occurred.
     */
    public String getRaw() {
        return raw;
    }

    /**
     * @return a textual description of this issue.
     */
    public String getReason() {
        return reason;
    }

    /**
     * @return the name of the file this issue occurred in.
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Provide four fields from this issue, separated by colons: systemId, line,
     * character, reason.
     */
    @Override
    public String toString() {
        return getSystemId() + ":" + getLine() + ":" + getCharacter() + ":" + getReason();
    }
}