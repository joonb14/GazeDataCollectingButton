# Android GazeDataCollectingButton
### This app is based on following examples
Camera2Api: https://github.com/android/camera-samples/tree/master/Camera2Basic <br>
The Result app looks like this <br><br>
<img width="300" src="https://user-images.githubusercontent.com/30307587/84726470-e2fff780-afc7-11ea-98d1-72bce874c28e.png">
<img width="300" src="https://user-images.githubusercontent.com/30307587/84726473-e3988e00-afc7-11ea-8546-8979ed3d6cbe.png">
<img width="300" src="https://user-images.githubusercontent.com/30307587/84726474-e4312480-afc7-11ea-8e20-4b3994f52f72.png">
### Application Details
This app collects Front Facing Camera Frame, Gyroscope, Accelerometer, touch (X,Y) position whenever you touch the button. In order to process and save Front Facing Camera Frame, I delayed starting new Activity by 1sec(1000ms). Otherwise you will face errors such as Camera Handler trying to send message to dead thread.The button will randomly appear in 5 x 7 grid.
### Check the Permissions before using the APP
I only ask for Camera permission on the runtime. So you need to check permissions in Application info, and grant External storage R/W permissions before you try collecting data
### Needs Modification for Others to Use
This app is based on Pixel 3 XL specifically. <br>
So if you are using other device, you have to change Button's left right margins<br>
If installed app captures Rear camera image, then you have to change the value at <br>Camera2BasicFragement.java Line 894~ if (mCameraId.equals("1")) ...... check what number equals to your device's front camera.
### Frame Resolution
My app's default Capturing frame resolution is 640x480.<br>
You can change this setting by modifying <br>Camera2BasicFragement.java Line 555: mImageReader = ImageReader.newInstance(480, 640, ImageFormat.JPEG, /*maxImages*/2);<br>
### Parse Saved Data
Image will be saved to /sdcard/CaptureApp/ directory <br>
If you run into problems in saving images, consider creating directory CaptureApp in /sdcard/ using adb shell then restart the App <br>
I saved all Front Facing Camera Frame, Gyroscope, Accelerometer, touch (X,Y) position in 1 image file. By storing Gyroscope, Accelerometer, touch (X,Y) position in file name. The file name is in following format<br> "pic_front_Datetime_picturecount_positionX_positionY_gyroscopeX_gyroscopeY_gyroscopeZ_accelerometerX_accelerometerY_accelerometerZ.jpg"<br>
I used Sensor.TYPE_GYROSCOPE, Sensor.TYPE_ACCELEROMETER which are calibrated data. If you want to get Raw data, check informations in https://developer.android.com/guide/topics/sensors/sensors_motion
