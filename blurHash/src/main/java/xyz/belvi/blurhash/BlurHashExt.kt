package xyz.belvi.blurhash

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import coil.request.ImageRequest
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.RequestCreator

// FOR GLIDE
fun RequestBuilder<Drawable>.blurPlaceHolder(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@blurPlaceHolder.placeholder(drawable)
            response(this@blurPlaceHolder)
        }
    }
}

fun RequestBuilder<Drawable>.blurPlaceHolder(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    targetView.post {
        blurPlaceHolder(blurString, targetView.width, targetView.height, blurHash, response)
    }
}

fun RequestOptions.blurPlaceHolderOf(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestOptions: RequestOptions) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@blurPlaceHolderOf.placeholder(drawable)
            response(this@blurPlaceHolderOf)
        }
    }
}

fun RequestOptions.blurPlaceHolderOf(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestOptions: RequestOptions) -> Unit
) {
    targetView.post {
        blurPlaceHolderOf(blurString, targetView.width, targetView.height, blurHash, response)
    }
}

// FOR COIL

fun ImageRequest.Builder.blurPlaceHolder(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: ImageRequest.Builder) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@blurPlaceHolder.placeholder(drawable)
            response(this@blurPlaceHolder)
        }
    }
}

@SuppressLint("CheckResult")
fun ImageRequest.Builder.blurPlaceHolder(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: ImageRequest.Builder) -> Unit
) {
    targetView.post {
        blurPlaceHolder(blurString, targetView.width, targetView.height, blurHash, response)
    }
}

// FOR PICASSO
fun RequestCreator.blurPlaceHolder(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: RequestCreator) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@blurPlaceHolder.placeholder(drawable)
            response(this@blurPlaceHolder)
        }
    }
}

@SuppressLint("CheckResult")
fun RequestCreator.blurPlaceHolder(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: RequestCreator) -> Unit
) {
    targetView.post {
        blurPlaceHolder(blurString, targetView.width, targetView.height, blurHash, response)
    }
}

// FOR IMAGEVIEW

fun ImageView.blurPlaceHolder(
    blurString: String,
    blurHash: BlurHash,
    response: (drawable: Drawable) -> Unit
) {
    this.post {
        if (width != 0 && height != 0) {
            blurHash.execute(blurString, width, height) { drawable ->
                if (getDrawable() != null)
                    setImageDrawable(drawable)
                response(drawable)
            }
        }
    }
}

// GENERIC USAGE
fun blurHashDrawable(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (drawable: Drawable) -> Unit
) {
    targetView.post {
        blurHashDrawable(blurString, targetView.width, targetView.height, blurHash, response)
    }
}

fun blurHashDrawable(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (drawable: Drawable) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            response(drawable)
        }
    }
}