# BluetoothStatusPlugin
Bluetooth Status Plugin

## 作用
实时获取蓝牙耳机的连接状态，播放按键的监听

## 用法示例

```
declare let cordova: any;

cordova.plugins.BluetoothStatusPlugin.start(msg => {
  alert(msg);
  console.log(msg);
});
 ```
### 参考
https://github.com/dongqishu/cordova-plugin-bluetoothstatus  

https://github.com/dongqishu/cordova-plugin-bluetoothbutton  

https://github.com/randdusing/cordova-plugin-bluetoothle/blob/master/src/ios/BluetoothLePlugin.m  

https://www.jianshu.com/p/b09553dcb191  

https://blog.csdn.net/alexander_wei/article/details/69664308  

http://www.cocoachina.com/bbs/read.php?tid=196755  

https://blog.csdn.net/lcl130/article/details/42141825  


音乐播放
https://github.com/zwand19/CordovaAppleMusic/blob/master/src/ios/CordovaAppleMusic.m

### 注意事项  

1.按键控制功能的前提，应该当前==播放==的是自己应用的==音乐==
