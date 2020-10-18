package xyz.belvi.blurhash

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.squareup.picasso.RequestCreator

// FOR GLIDE
fun RequestBuilder<Drawable>.withBlurHash(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@withBlurHash.placeholder(drawable)
            response(this@withBlurHash)
        }
    }
}

@SuppressLint("CheckResult")
fun RequestBuilder<Drawable>.withBlurHash(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    targetView.post {
        if (targetView.width != 0 && targetView.height != 0) {
            blurHash.execute(blurString, targetView.width, targetView.height) { drawable ->
                this@withBlurHash.placeholder(drawable)
                response(this@withBlurHash)
            }
        }
    }
}

// FOR PICASSO
fun RequestCreator.withBlurHash(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: RequestCreator) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@withBlurHash.placeholder(drawable)
            response(this@withBlurHash)
        }
    }
}

@SuppressLint("CheckResult")
fun RequestCreator.withBlurHash(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: RequestCreator) -> Unit
) {
    targetView.post {
        if (targetView.width != 0 && targetView.height != 0) {
            blurHash.execute(blurString, targetView.width, targetView.height) { drawable ->
                this@withBlurHash.placeholder(drawable)
                response(this@withBlurHash)
            }
        }
    }
}

// FOR IMAGEVIEW

fun ImageView.placeHolder(blurString: String, blurHash: BlurHash) {
    this.post {
        if (width != 0 && height != 0) {
            blurHash.execute(blurString, width, height) { drawable ->
                if (getDrawable() != null)
                    setImageDrawable(drawable)
            }
        }
    }
}