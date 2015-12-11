package org.example.training.mocks

/**
 * Created by pledbrook on 12/10/15.
 */
class RestClient {
    String getContent(String url) {
        return new URL(url).text
    }
}
