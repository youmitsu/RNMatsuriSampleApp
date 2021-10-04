// @flow
import { NativeModules, NativeEventEmitter } from 'react-native'

const { StreamingModule } = NativeModules
const eventEmitter = new NativeEventEmitter(StreamingModule)

eventEmitter.addListener('SomethingEvent', (event) => {
  // something to do
})

export const startStreaming = async (): Promise<void> => {
  await StreamingModule.startStreaming()
}

export const stopStreaming = async (): Promise<void> => {
  await StreamingModule.stopStreaming()
}

export const addListener = (eventName: string, callback: (event: Object) => void): Object => {
  const eventListener = eventEmitter.addListener(eventName, callback)
  return eventListener
}

export const removeListener = (eventListener: Object) => {
  eventListener.remove()
}
