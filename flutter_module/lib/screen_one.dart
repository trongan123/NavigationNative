import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = MethodChannel('com.navigation.flutter/screen.one');

class ScreenOnePage extends StatefulWidget {
  const ScreenOnePage({super.key, required this.title});

  final String title;

  @override
  State<ScreenOnePage> createState() => _ScreenOnePageState();
}

class _ScreenOnePageState extends State<ScreenOnePage> {
  void sendEventToNative(String action) async {
    try {
      await platform.invokeMethod('onFlutterEvent', action);
    } on PlatformException catch (e) {
      print("Failed to send event: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text('Screen One'),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Present");
              },
              child: const Text("Present"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Push");
              },
              child: const Text("Push"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Back");
              },
              child: const Text("Back"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("ClearStack");
              },
              child: const Text("Push and Clear Stack"),
            ),
            const SizedBox(height: 10),
          ],
        ),
      ),
    );
  }
}
