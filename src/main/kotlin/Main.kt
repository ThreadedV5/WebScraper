package org.example
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


fun main() {
    val url: String = "https://asuracomic.net/series/bad-born-blood-00b103d8/chapter/53"
    val doc: Document = fetchDocument(url)
    val products = mutableListOf<Product>()

    val productElements: Elements = doc.select("img")
    println("")
    for (element in productElements) {
        val product = Product()
        product.url   = element.attr("src")
        // product.image = productElement.selectFirst("img")?.attr("src")
        // product.name  = productElement.selectFirst("h2")?.text()
        // product.price = productElement.selectFirst("span")?.text()
        products.add(product)
    }

    println(products)
}

fun fetchDocument(url: String): Document {
    val doc: Document? = runCatching {
        Jsoup
            .connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
            .header("Accept-Language", "*")
            .get();
    }.getOrNull()
    return doc!!
}

open class Product() {
    var url: String? = null
    var image: String? = null
    var name: String? = null
    var price: String? = null

    override fun toString(): String {
        return ("{ \"url\":\"" + url + "\", "
                + " \"image\": \"" + image + "\", "
                + "\"name\":\"" + name + "\", "
                + "\"price\": \"" + price + "\" }")
    }

}