package xyz.belvi.blurhash

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.LruCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlurHash(private var context: Context, lruSize: Int = 10) {
    private var data: LruCache<String, BitmapDrawable> = LruCache(lruSize)
    private val scope = CoroutineScope(Dispatchers.Main)

    fun clean() {
        scope.cancel()
        data.evictAll()
    }

    fun execute(
        blurString: String,
        width: Int,
        height: Int,
        response: (drawable: BitmapDrawable) -> Unit
    ) {
        scope.launch {
            var blurBitmap = getBlurDrawable(blurString)
            withContext(Dispatchers.IO) {
                blurBitmap ?: run {
                    val bitmap = BlurHashDecoder.decode(
                        blurString,
                        width,
                        height,
                        useCache = false
                    )
                    blurBitmap = BitmapDrawable(
                        context.resources,
                        bitmap
                    )
                    cache(blurString, blurBitmap!!)
                }
            }
            response(blurBitmap!!)
        }
    }

    private fun cache(blurString: String, drawable: BitmapDrawable) {
        data.put(blurString, drawable)
    }

    private fun getBlurDrawable(blurString: String): BitmapDrawable? {
        return data.get(blurString)
    }
}
