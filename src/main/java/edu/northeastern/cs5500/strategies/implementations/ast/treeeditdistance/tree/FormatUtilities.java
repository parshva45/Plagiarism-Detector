package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @author namratabilurkar
 */
public class FormatUtilities {
	
	/**
	 * @param line
	 * @param separator
	 * @return new String[0] for empty or null lines, string-array containing fields, otherwise
	 */
	public static String[] getFields(String line,
			char separator) {
		if ((line != null) && (!line.equals(""))) {
			StringBuffer field = new StringBuffer();
			LinkedList fieldArr = new LinkedList();
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (ch == separator) {
					fieldArr.add(field.toString().trim());
					field = new StringBuffer();
				} else {
					field.append(ch);
				}
			}
			fieldArr.add(field.toString().trim());
			return (String[])fieldArr.toArray(new String[fieldArr.size()]);
		} else {
			return new String[0];
		}
	}

	/**
	 * Find the position of the matching bracket
	 * @param s is the string
	 * @param pos of the cursor
	 * @return the position of the matching bracket
	 */
	public static int matchingBracket(String s, int pos) {
		if ((s == null) || (pos > s.length() - 1)) {
			return -1;
		}
		char open = s.charAt(pos);
		char close;
		switch (open) {
		case '{':
			close = '}';
			break;
		case '(':
			close = ')';
			break;
		case '[':
			close = ']';
			break;
		case '<':
			close = '>';
			break;
		default:
			return -1;
		}

		pos++;
		int count = 1;
		while ((count != 0) && (pos < s.length())) {
			if (s.charAt(pos) == open) {
				count++;
			} else if (s.charAt(pos) == close) {
				count--;
			}
			pos++;
		}
		if (count != 0) {
			return -1;
		} else {
			return pos - 1;
		}
	}

	/**
	 * Determine the ID of the tree
	 * @param s is the string
	 * @return ID of the tree
	 */
	public static int getTreeID(String s) {
		if ((s != null) && (s.length() > 0)) {
			int end = s.indexOf(':', 1);
			if (end == -1) {
				return -1;
			} else {
				return Integer.parseInt(s.substring(0, end));
			}
		} else {
			return -1;
		}
	}

	/**
	 * Returns the root of the tree
	 * @param s tree
	 * @return root of the tree
	 */
	public static String getRoot(String s) {
		if ((s != null) && (s.length() > 0) && s.startsWith("{") && s.endsWith("}")) {
			int end = s.indexOf('{', 1);
			if (end == -1) {
				end = s.indexOf('}', 1);
			}
			return s.substring(1, end);
		} else {
			return null;
		}
	}

	/**
	 * Find the list of children
	 * @param s is the tree string
	 * @return list of children of the tree
	 */
	public static Vector getChildren(String s) {
		if ((s != null) && (s.length() > 0) && s.startsWith("{") && s.endsWith("}")) {
			Vector children = new Vector();
			int end = s.indexOf('{', 1);
			if (end == -1) {
				return children;
			}
			String rest = s.substring(end, s.length() - 1);
			int match = 0;
			while ((rest.length() > 0) && ((match = matchingBracket(rest, 0)) != -1)) {
				children.add(rest.substring(0, match + 1));
				if (match + 1 < rest.length()) {
					rest = rest.substring(match + 1);
				} else {
					rest = "";
				}
			}
			return children;
		} else {
			return null;
		}
	}
}
