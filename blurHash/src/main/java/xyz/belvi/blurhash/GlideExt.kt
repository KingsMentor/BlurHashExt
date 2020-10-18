package xyz.belvi.blurhash

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.RequestBuilder

fun RequestBuilder<Drawable>.withBlurHash(
    resource: Resources,
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    useBlurMapCache: Boolean = false
): RequestBuilder<Drawable> {
    val blurBitmap = BlurHashDecoder.decode(blurString, width, height, useCache = useBlurMapCache)
    return this.placeholder(BitmapDrawable(resource, blurBitmap))
}

fun RequestBuilder<Drawable>.withBlurHash(
    blurString: String,
    targetView: View,
    useBlurMapCache: Boolean = false
): RequestBuilder<Drawable> {
    if (targetView.width == 0 || targetView.height == 0)
        return this
    val blurBitmap = BlurHashDecoder.decode(
        blurString,
        targetView.width,
        targetView.height,
        useCache = useBlurMapCache
    )
    return this.placeholder(BitmapDrawable(targetView.context.resources, blurBitmap))
}