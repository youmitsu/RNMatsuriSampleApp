// @flow
import { NativeModules } from 'react-native'

const { ToastModule }  = NativeModules

export const showToast = (text: string) => {
  ToastModule.showToast(text)
}