import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = MethodChannel('com.navigation.flutter/present.two');

class PresentTwoPage extends StatefulWidget {
  const PresentTwoPage({super.key, required this.title});

  final String title;

  @override
  State<PresentTwoPage> createState() => _PresentTwoPageState();
}

class _PresentTwoPageState extends State<PresentTwoPage> {
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
            const Text('Present Two'),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Back");
              },
              child: const Text("Back"),
            )
          ],
        ),
      ),
    );
  }
}
