# CS310App
USClassifieds

In order to run the app properly, it's required to setup Google Maps API key.
1. Open SDK manager
2. Click on SDK tools tab
3. Check Google Play Services
4. Click apply
5. Save and close
6. In... res/val/google_maps_api.xml replace the last sentence with
 
 <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">YOUR_KEY</string>
 
 where YOUR_KEY is the API key that can be easily obtained by following the steps at 

https://console.developers.google.com/flows/enableapi?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID&r=1A:EB:87:F8:53:0D:BA:0C:FD:45:F9:63:9C:B5:AC:18:58:24:CC:CA%3Bcom.example.cs310app

Now the app is ready to be ran.
