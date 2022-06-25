/* Copyright (c) 2003 The Nutch Organization.  All rights reserved.   */
/* Use subject to the conditions in http://www.nutch.org/LICENSE.txt. */

package net.nutch.analysis;

import java.io.*;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Token;

/** The tokenizer used for Nutch document text.  Implemented in terms of our
 * JavaCC-generated lexical analyzer, {@link NutchAnalysisTokenManager}, shared
 * with the query parser.
 */
public final class NutchDocumentTokenizer extends Tokenizer
  implements NutchAnalysisConstants {
  
  private NutchAnalysisTokenManager tokenManager;

  /** Construct a tokenizer for the text in a Reader. */
  public NutchDocumentTokenizer(Reader reader) {
    super(reader);
    tokenManager = new NutchAnalysisTokenManager(reader); 
  }
  
  /** Returns the next token in the stream, or null at EOF. */
  public final Token next() throws IOException {

    net.nutch.analysis.Token t;

    try {
      loop: {
        while (true) {
          t = tokenManager.getNextToken();
          switch (t.kind) {                       // skip query syntax tokens
          case EOF: case WORD: case ACRONYM: case SIGRAM:
            break loop;
          default:
          }
        }
      }
    } catch (TokenMgrError e) {                   // translate exceptions
      throw new IOException("Tokenizer error:" + e);
    }

    if (t.kind == EOF)                            // translate tokens
      return null;
    else {
      return new Token(t.image,t.beginColumn,t.endColumn,tokenImage[t.kind]);
    }
  }

  /** For debugging. */
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.print("Text: ");
      String line = in.readLine();
      Tokenizer tokenizer = new NutchDocumentTokenizer(new StringReader(line));
      Token token;
      System.out.print("Tokens: ");
      while ((token = tokenizer.next()) != null) {
        System.out.print(token.termText());
        System.out.print(" ");
      }
      System.out.println();
    }
  }

}
