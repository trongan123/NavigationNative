import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'screen_one.dart';
import 'screen_two.dart';
import 'screen_three.dart';
import 'present_one.dart';
import 'present_two.dart';
import 'screen_search.dart';

void main() => runApp(MaterialApp(
  initialRoute: '/',
  routes: {
    '/': (context) => Container(),
    '/screen_one': (context) => ScreenOnePage(title: 'Screen One'),
    '/screen_two': (context) => ScreenTwoPage(title: 'Screen Two'),
    '/screen_three': (context) => ScreenThreePage(title: 'Screen Three'),
    '/present_one': (context) => PresentOnePage(title: 'Present One'),
    '/present_two': (context) => PresentTwoPage(title: 'Present Two'),
    '/screen_search': (context) => ScreenSearchPage(title: 'Screen Search'),
  },
));

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

const platform = MethodChannel('com.navigation.flutter/events');

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {

      _counter++;
    });
  }

  void sendEventToNative(String eventName) async {
    try {
      await platform.invokeMethod('onFlutterEvent', eventName);
    } on PlatformException catch (e) {
      print("Failed to send event: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text('You have pushed the button this many times:'),
              Text(
                '$_counter',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {

                },
                child: const Text("Button 1"),
              ),
              ElevatedButton(
                onPressed: () {

                },
                child: const Text("Button 2"),
              ),
              ElevatedButton(
                onPressed: () {

                },
                child: const Text("Button 3"),
              ),
              ElevatedButton(
                onPressed: () {
                  // Xử lý khi nhấn button 3
                  sendEventToNative("avczxcxzxczxc");
                },
                child: const Text("Back"),
              ),
              const SizedBox(height: 10),
            ],
          ),
        )
    );
  }
}