# BlurHashExt
kt extensions of [BlurHash](https://blurha.sh/) for Glide and Piccasso.

Based Blurhash implementation is from https://github.com/woltapp/blurhash. 

This library work is focus on optimizing it for Android for faster placehold images.


How BlurHash works?
In short, BlurHash takes an image, and gives you a short string (only 20-30 characters!) that represents the placeholder for this image. You do this on the backend of your service, and store the string along with the image. When you send data to your client, you send both the URL to the image, and the BlurHash string. Your client then takes the string, and decodes it into an image that it shows while the real image is loading over the network. The string is short enough that it comfortably fits into whatever data format you use. For instance, it can easily be added as a field in a JSON object.
