//
//  CDVBluetooth.h
//  
//
//  Created by chenlounai on 18/12/5.
//  
//


#import <Cordova/CDVPlugin.h>
#import <CoreBluetooth/CoreBluetooth.h>

@interface CDVBluetooth  : CDVPlugin<CBCentralManagerDelegate,CBPeripheralDelegate>{
  NSString* initCallback;
    
}
- (void)start:(CDVInvokedUrlCommand*)command;
- (void)stop:(CDVInvokedUrlCommand*)command;


@end
