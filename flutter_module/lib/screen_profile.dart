import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = MethodChannel('com.navigation.flutter/profile');

class ScreenProfilePage extends StatefulWidget {
  const ScreenProfilePage({super.key, required this.title});

  final String title;

  @override
  State<ScreenProfilePage> createState() => _ScreenProfilePageState();
}

class _ScreenProfilePageState extends State<ScreenProfilePage> {
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
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[Text("Screen Profile")],
        ),
      ),
    );
  }
}
