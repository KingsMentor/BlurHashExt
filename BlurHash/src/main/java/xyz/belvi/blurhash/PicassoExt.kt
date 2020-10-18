package xyz.belvi.blurhash

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.RequestBuilder
import com.squareup.picasso.RequestCreator

fun RequestCreator.withBlurHash(
    resource: Resources,
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    useBlurMapCache: Boolean = false
): RequestCreator {
    val blurBitmap = BlurHashDecoder.decode(blurString, width, height, useCache = useBlurMapCache)
    return this.placeholder(BitmapDrawable(resource, blurBitmap))
}

fun RequestCreator.withBlurHash(
    blurString: String,
    targetView: View,
    useBlurMapCache: Boolean = false
): RequestCreator {
    val blurBitmap = BlurHashDecoder.decode(
        blurString,
        targetView.width,
        targetView.height,
        useCache = useBlurMapCache
    )
    return this.placeholder(BitmapDrawable(targetView.context.resources, blurBitmap))
}