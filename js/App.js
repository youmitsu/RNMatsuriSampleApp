// @flow
import React, {
  useState,
  useCallback,
  useEffect,
} from 'react';
import type {Node} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  Pressable,
  Button,
} from 'react-native';

import {
  showToast
} from './modules/toast'

import {
  requestPermission,
  startRecording,
  stopRecording,
} from './modules/recorder'

import * as Streaming from './modules/streaming'

const App: () => Node = () => {
  const [isPermissionGranted, setPermissionGranted] = useState(false)
  const [isRecording, setIsRecording] = useState(false)
  const [isStreaming, setIsStreaming] = useState(false)
  const [recordedFilePath, setRecordedFilePath] = useState(false)

  // useEffect(() => {
  //   const run = async () => {
  //     const granted = await requestPermission()
  //     setPermissionGranted(granted)
  //   }
  //   run()
  // }, [])

  // useEffect(() => {
  //   const listener = Streaming.addListener('onSuccessStream', (event) => {
  //     showToast('ストリーミングの開始に成功しました')
  //   })
  //   return () => {
  //     listener.remove()
  //   }
  // }, [showToast])

  // useEffect(() => {
  //   const listener = Streaming.addListener('onError', (event) => {
  //     showToast('ストリーミングに失敗しました')
  //   })
  //   return () => {
  //     listener.remove()
  //   }
  // }, [showToast])

  const _showToast = useCallback(() => {
    showToast('Tapped!')
  }, [])

  // const onStartRecordingPressed = useCallback(async () => {
  //   try {
  //     await startRecording()
  //     setIsRecording(true)
  //   } catch (err) {
  //     showToast('Error is ocurred!')
  //     setIsRecording(false)
  //   }
  // }, [])

  // const onStopRecordingPressed = useCallback(async () => {
  //   try {
  //     const filePath = await stopRecording()
  //     setRecordedFilePath(filePath)
  //   } catch (err) {
  //     // error-handling
  //   } finally {
  //     setIsRecording(false)
  //   }
  // }, [])

  // const onStartStreamingPressed = useCallback(async () => {
  //   await Streaming.startStreaming()
  //   setIsStreaming(true)
  // }, [])

  // const onStopStreamingPressed = useCallback(async () => {
  //   await Streaming.stopStreaming()
  //   setIsStreaming(true)
  // }, [])

  const renderRecordingButton = useCallback(() => {
    if (isRecording) {
      return (
        <>
          <Button
            title="stopRecording"
            onPress={onStopRecordingPressed}
          />
        </>
      )
    }
    return (
      <>
        <Button
          title="startRecording"
          onPress={onStartRecordingPressed}
        />
      </>
    )
  })

  const renderStreamingButton = useCallback(() => {
    if (isStreaming) {
      return (
        <>
          <Button
            title="stopStreaming"
            onPress={onStopStreamingPressed}
          />
        </>
      )
    }
    return (
      <>
        <Button
          title="startStreaming"
          onPress={onStartStreamingPressed}
        />
      </>
    )
  })

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar />
      <View style={styles.container}>
        <Pressable onPress={_showToast} style={styles.toastButton}>
          <Text style={styles.toastButtonText}>
            SHOW TOAST!
          </Text>
        </Pressable>
        <View style={styles.divider}/>
        <View style={styles.recorderRow}>
          <Pressable onPress={_showToast} style={styles.toastButton}>
            <Text style={styles.toastButtonText}>
              START RECORDING
            </Text>
          </Pressable>
          <View style={styles.columnDivider}/>
          <Pressable onPress={_showToast} style={styles.toastButton}>
            <Text style={styles.toastButtonText}>
              STOP RECORDING
            </Text>
          </Pressable>
        </View>
        <View style={styles.divider}/>
        <View style={styles.recorderRow}>
          <Pressable onPress={_showToast} style={styles.toastButton}>
            <Text style={styles.toastButtonText}>
              START STREAMING
            </Text>
          </Pressable>
          <View style={styles.columnDivider}/>
          <Pressable onPress={_showToast} style={styles.toastButton}>
            <Text style={styles.toastButtonText}>
              STOP STREAMING
            </Text>
          </Pressable>
        </View>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
  },
  toastButton: {
    padding: 10,
    borderRadius: 8,
    backgroundColor: "#ff75ad"
  },
  toastButtonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  recorderRow: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  divider: {
    height: 30,
  },
  columnDivider: {
    width: 10,
  },
  recorderButtonContainer: {
  }
});

export default App;
