var exec = require('cordova/exec');

// exports.getDeviceNetwokActivity = function(success, error) {
// 	exec(success, error, "advance_network", "getDeviceNetwokActivity");
// };
exports.setDeviceNetwork = function(data,success, error) {
	exec(success, error, "advance_network", "setDeviceNetwork");
};
