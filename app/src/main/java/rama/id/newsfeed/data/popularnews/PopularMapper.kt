package rama.id.newsfeed.data.popularnews

import rama.id.newsfeed.data.popularnews.cloud.response.PopularResponse
import rama.id.newsfeed.data.popularnews.cloud.response.toSource
import rama.id.newsfeed.domain.popularnews.PopularNews

/**
 * transform mapper u can sorting wich u wont to use from response model
 * and use the only what u want to use
 */
fun transform (value: PopularResponse) = PopularNews(
value.source?.let(::toSource)?: throw NoSuchElementException("Require value Source"),
value.author ?: throw NoSuchElementException("required author"),
value.title ?:throw NoSuchElementException("required title"),
value.description ?: throw NoSuchElementException("required description"),
value.url?: throw NoSuchElementException("required url"),
value.urlToImage ?: throw NoSuchElementException("required urlToImage"),
value.publishedAt ?: throw NoSuchElementException("required publishedat"),
value.content ?: throw NoSuchElementException("required content")
)