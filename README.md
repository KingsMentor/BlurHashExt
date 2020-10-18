# BlurHashExt

[ ![Download](https://api.bintray.com/packages/kingsmentor/maven/blurHash/images/download.svg) ](https://bintray.com/kingsmentor/maven/blurHash/_latestVersion)
[![HitCount](http://hits.dwyl.com/kingsmentor/blurHash.svg)](http://hits.dwyl.com/kingsmentor/blurHash)

kt extensions of [BlurHash](https://blurha.sh/) for Glide and Picasso.

Based Blurhash implementation is from https://github.com/woltapp/blurhash. 

This library work is focus on optimizing it for Android for faster placehold images.


### How BlurHash works?


In short, BlurHash takes an image, and gives you a short string (only 20-30 characters!) that represents the placeholder for this image. You do this on the backend of your service, and store the string along with the image. When you send data to your client, you send both the URL to the image, and the BlurHash string. Your client then takes the string, and decodes it into an image that it shows while the real image is loading over the network. The string is short enough that it comfortably fits into whatever data format you use. For instance, it can easily be added as a field in a JSON object.

### In summary:

<img src="Media/HowItWorks1.jpg" width="250">&nbsp;&nbsp;&nbsp;<img src="Media/HowItWorks2.jpg" width="250">

Want to know all the gory technical details? Read the [algorithm description](Algorithm.md).


### Download

Gradle:
```gradle
dependencies {
  implementation 'xyz.belvi:blurHash:1.0.0'
}
```

### Usage

#### Step 1 - Initialize BlurHash

`val blurHash: BlurHash = BlurHash(this, lruSize = 20)`

`lruSize` determines the number of blur drawable that will be cache in memory. The default size is 10

#### Step 2 - Using BlurHash

**With Glide**
```kotlin
    Glide.with(this).load(imgUrl)
    .withBlurHash(blurHashString, imageView, blurHash)
    {
        requestBuilder ->
        requestBuilder.into(imageView)
    }
 ```
 
 or
 
 ```kotlin
    Glide.with(this).load(imgUrl)
    .withBlurHash(blurHashString, imageView, width = 200, height= 200, blurHash = blurHash)
    {
        requestBuilder ->
        requestBuilder.into(imageView)
    }
 ```
 
 
**With Picasso**
```kotlin
    Picasso.get().load(imgUrl)
    .withBlurHash(blurHashString, imageView, blurHash)
    {
        request ->
        request.into(imageView)
    }
 ```
 
 or
 
 ```kotlin
    Picasso.get().load(imgUrl)
    .withBlurHash(blurHashString, width = 200, height= 200, blurHash = blurHash)
    {
        request ->
        request.into(imageView)
    }
 ```
 
**In an ImageView**

This is useful for loading a placeholder before makeing a call to load the actual Image
```kotlin
        imageView.placeHolder(blurHashString, blurHash)
        {
            imageView.setImageURI(imgUrl)
        }
 ```
 
 #### Step 3 - Finally, Disposing
 
On `onDestroy`, do not forget to clean cached bitmap and tell  `BlurHash` to cancel any pending coroutine transaction. Here's how :

```kotlin
    override fun onDestroy() {
        super.onDestroy()
        blurHash.clean()
    }
```

### What is the `punch` parameter in some of these implementations?

It is a parameter that adjusts the contrast on the decoded image. 1 means normal, smaller values will make the effect more subtle,
and larger values will make it stronger. This is basically a design parameter, which lets you adjust the look.

Technically, what it does is scale the AC components up or down.


## Contributing

Contributions are welcomed even to [blurHash base library](https://github.com/woltapp/blurhash/)! 

You can also contribute by reporting issues or helping to resolve issues listed here [issue tracker](https://github.com/kingsmentor/blurhash/issues) or 
file a pull request.
