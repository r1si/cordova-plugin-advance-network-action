# Cordova plugin advance network action

A Cordova plugin to do some network action, for now only on android
Here what you can do:
* Change network 
* get network activty


## Installation

Cordova: 
```
cordova plugin add cordova-advance-network-action
```

Phonegap:
```
phonegap plugin add cordova-advance-network-action
```

## Usage

### changeNetwork 
[ANDROID][ROOT REQUIRED]
Function to change network activity with the following value:  
* "2G" 
* "3G"
* "4G"
* "2G/3G"
* "2G/3G/4G"

It returns true if can change network, else false if it fails

```
advance_network.changeNetwork("2G",function(res) {
    if(res == "true"){
        console.log("network change!");
    }else{
        console.log("Eror during changing network");
    }
}, function() {
    console.error('An error occoured while do the command');
});
```

### getDeviceNetwokActivity
Function that get the current device network activity  
It return an int number with this value:
* 0 = DATA_ACTIVITY_NONE - Data connection activity: No traffic.  
* 1 = DATA_ACTIVITY_IN - Data connection activity: Currently receiving IP PPP traffic.  
* 2 = DATA_ACTIVITY_OUT - Data connection activity: Currently sending IP PPP traffic.  
* 3 = DATA_ACTIVITY_INOUT - Data connection activity: Currently both sending and receiving IP PPP traffic.  
* 4 = DATA_ACTIVITY_DORMANT - Data connection is active, but physical link is down  

```
advance_network.getDeviceNetwokActivity(function(res) {
        alert(res); // debug value
    },function(res) {
        console.error("Error form plugin");
    });
```