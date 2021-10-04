// @flow
import { NativeModules, PermissionsAndroid } from 'react-native'

const { RecorderModule } = NativeModules

export const requestPermission = async (): Promise<boolean> => {
  try {
    const granted = await PermissionsAndroid.request(
      PermissionsAndroid.PERMISSIONS.RECORD_AUDIO,
      {
        title: "Cool Photo App Camera Permission",
        message: "音声の録音に使用します。許可しますか",
        buttonNegative: "Cancel",
        buttonPositive: "OK"
      }
    )
    return granted === PermissionsAndroid.RESULTS.GRANTED
  } catch (err) {
    console.error(err)
    return false
  }
}

export const startRecording = async () => {
  try {
    await RecorderModule.startRecording()
  } catch (err) {
    console.error(err)
    // error handling
  }
}

export const stopRecording = async (): Promise<?string> => {
  try {
    const filePath = await RecorderModule.stopRecording()
    return filePath
  } catch (err) {
    console.error(err)
    return null
    // error handling
  }
}