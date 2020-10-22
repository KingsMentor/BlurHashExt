package xyz.belvi.blurhash

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
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

@SuppressLint("CheckResult")
fun RequestBuilder<Drawable>.blurPlaceHolder(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    targetView.post {
        if (targetView.width != 0 && targetView.height != 0) {
            blurHash.execute(blurString, targetView.width, targetView.height) { drawable ->
                this@blurPlaceHolder.placeholder(drawable)
                response(this@blurPlaceHolder)
            }
        }
    }
}

@SuppressLint("CheckResult")
fun RequestOptions.blurPlaceHolderOf(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestOptions: RequestOptions) -> Unit
) {
    targetView.post {
        if (targetView.width != 0 && targetView.height != 0) {
            blurHash.execute(blurString, targetView.width, targetView.height) { drawable ->
                this@blurPlaceHolderOf.placeholder(drawable)
                response(this@blurPlaceHolderOf)
            }
        }
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
        if (targetView.width != 0 && targetView.height != 0) {
            blurHash.execute(blurString, targetView.width, targetView.height) { drawable ->
                this@blurPlaceHolder.placeholder(drawable)
                response(this@blurPlaceHolder)
            }
        }
    }
}

// FOR IMAGEVIEW

fun ImageView.placeHolder(
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