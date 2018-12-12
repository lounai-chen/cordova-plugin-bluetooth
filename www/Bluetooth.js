var exec = require('cordova/exec');

exports.start = function(success, fail) {
  return cordova.exec(success, fail, 'Bluetooth', 'start', [{}]);
};
exports.stop = function(success, fail) {
  return cordova.exec(success, fail, 'Bluetooth', 'stop', [{}]);
};