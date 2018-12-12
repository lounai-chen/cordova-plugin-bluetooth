# BluetoothStatusPlugin
Bluetooth Status Plugin

## 作用
实时获取蓝牙耳机的连接状态

## 用法示例

```
declare let cordova: any;

cordova.plugins.BluetoothStatusPlugin.start(msg => {
  alert(msg);
  console.log(msg);
});
 ```

