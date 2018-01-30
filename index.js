import React from 'react';
import {AppRegistry, StyleSheet, Text, View, TouchableOpacity, Dimensions} from 'react-native';
import ToastExample from './ToastExample';
import Button from './src/components/Button';
import ProgressBar from './src/components/ProgressBar'

class HelloWorld extends React.Component {

  constructor() {
    super()
    this.onButtonPressHandler = this.onButtonPressHandler.bind(this)
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>It is a React Native View!</Text>
        <Button 
          style={styles.buttonStyle}
          onPress={this.onButtonPressHandler}>
            Toast
        </Button>
        <ProgressBar
            progress={0}
            indeterminate={true}
            style={styles.progressBar} />     
         </View>
    );
  }

  onButtonPressHandler() {
    //ToastExample.getDataKey()

    var myIntentHelper = require('./src/helpers/MyIntentHelper')
    myIntentHelper.launchGenericIntent('com.example.anandmishra.demoapplication.LauncherActivity')
    .then(() => console.log('Succesfully Done'))
    .catch(() => console.log('Got Error'))
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  buttonStyle: {
    flex: 1
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  progressBar: {
    height: 50,
    // flex: 1,
    margin: 20,
    width: Dimensions.get('window').width - 2 * 20,
  }
});

AppRegistry.registerComponent('MyReactNativeApp', () => HelloWorld);
