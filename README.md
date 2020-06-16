# Android GazeDataCollectingButton
### This app is based on following examples
Camera2Api: https://github.com/android/camera-samples/tree/master/Camera2Basic <br>
The Result app looks like this <br><br>
<img width="300" src="https://user-images.githubusercontent.com/30307587/84738282-970f7b80-afe4-11ea-822c-ed284de1b6a0.png">
<img width="300" src="https://user-images.githubusercontent.com/30307587/84738277-95de4e80-afe4-11ea-8ecb-fb3cd6bec28a.png">
<img width="300" src="https://user-images.githubusercontent.com/30307587/84738280-9676e500-afe4-11ea-8dba-6a6516e1aeb8.png">
### Application Details
This app collects Front Facing Camera Frame, Gyroscope, Accelerometer, touch (X,Y) position whenever you touch the button. In order to process and save Front Facing Camera Frame, I delayed starting new Activity by 1sec(1000ms). Otherwise you will face errors such as Camera Handler trying to send message to dead thread.<br>
The button will randomly appear in 5 x 7 grid.
### Check the Permissions before using the APP
I only ask for Camera permission on the runtime. So you need to check permissions in Application info, and grant External storage R/W permissions before you try collecting data
### Needs Modification for Others to Use
This app is based on Pixel 3 XL specifically. <br>
So if you are using other device, you have to change Button's left right margins<br><br>
Please take a look at Camera2BasicFragment.java Line 960 onClick Mehod. At Line 967,968,969 you should change the button size and margins. Also should look into topmargin and leftmargin varaibles <br><br>
<img width="800" src="https://user-images.githubusercontent.com/30307587/84727100-48a0b380-afc9-11ea-89f7-44e4161528ad.png"><br>
If installed app captures Rear camera image, then you have to change the value at <br>Camera2BasicFragement.java Line 894~ if (mCameraId.equals("1")) ...... check what number equals to your device's front camera.
### Frame Resolution
My app's default Capturing frame resolution is 640x480.<br>
You can change this setting by modifying <br>Camera2BasicFragement.java Line 555: mImageReader = ImageReader.newInstance(480, 640, ImageFormat.JPEG, /*maxImages*/2);<br>
### Parse Saved Data
Image will be saved to /sdcard/CaptureApp/ directory <br>
If you run into problems in saving images, consider creating directory CaptureApp in /sdcard/ using adb shell then restart the App <br>
I saved all Front Facing Camera Frame, Gyroscope, Accelerometer, touch (X,Y) position in 1 image file. By storing Gyroscope, Accelerometer, touch (X,Y) position in file name. The file name is in following format<br> "pic_front_Datetime_picturecount_positionX_positionY_gyroscopeX_gyroscopeY_gyroscopeZ_accelerometerX_accelerometerY_accelerometerZ.jpg"<br>
I used Sensor.TYPE_GYROSCOPE, Sensor.TYPE_ACCELEROMETER which are calibrated data. If you want to get Raw data, check informations in https://developer.android.com/guide/topics/sensors/sensors_motion
