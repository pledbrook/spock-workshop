package org.example.training

/**
 * Created by pledbrook on 10/12/2015.
 */
class WordStats {
    private String content

    WordStats() {
        content = getClass().getClassLoader().getResource("README.txt").getText("UTF-8")
    }

    WordStats(url) {
        content = url.getText("UTF-8")
    }

    int getCharCount() { return content.size() }
    int getLineCount() { content ? content.split(/\n/).size() : 0 }
    int getSingleLetterCount() { (content =~ /\b\w\b/).count }
    int getAllCapsCount() { (content =~ /\b[A-Z]+\b/).count }
}
