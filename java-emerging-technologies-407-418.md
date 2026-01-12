# 🔵 28. Emerging Technologies

# 🔹 Future Technologies

### Question 407: What is Project Loom?

**Answer (35 seconds):**
* OpenJDK project introducing lightweight threads (virtual threads) to Java
* **Virtual Threads**: Millions of threads with minimal memory overhead
* **Structured Concurrency**: Better way to manage concurrent operations
* **Blocking Operations**: Virtual threads can block without OS thread blocking
* **Scalability**: Handle massive concurrent workloads efficiently
* **Backward Compatible**: Works with existing thread-based code
* **Available**: Preview in Java 19, stable in Java 21

```java
// Project Loom - Virtual Threads example
public class VirtualThreadsExample {
    
    public static void main(String[] args) throws InterruptedException {
        // Create virtual thread
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Running in virtual thread: " + Thread.currentThread());
            try {
                Thread.sleep(1000); // Doesn't block OS thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Create millions of virtual threads efficiently
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1_000_000; i++) {
                executor.submit(() -> {
                    // Each task runs in its own virtual thread
                    performIOOperation();
                });
            }
        }
        
        virtualThread.join();
    }
    
    private static void performIOOperation() {
        // Simulate I/O operation - virtual thread yields efficiently
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

---

### Question 408: What is Project Panama?

**Answer (30 seconds):**
* OpenJDK project improving Java's interaction with native code
* **Foreign Function Interface**: Call native functions without JNI
* **Foreign Memory API**: Direct access to off-heap memory
* **Vector API**: SIMD operations for better performance
* **Performance**: Eliminates JNI overhead and complexity
* **Safety**: Type-safe native memory access
* **Interoperability**: Better integration with C/C++ libraries

```java
// Project Panama - Foreign Function Interface example
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class PanamaExample {
    
    public static void main(String[] args) throws Throwable {
        // Load native library
        Linker linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();
        
        // Find strlen function from C standard library
        MemorySegment strlenAddress = stdlib.find("strlen").orElseThrow();
        
        // Create method handle for strlen
        FunctionDescriptor strlenDescriptor = FunctionDescriptor.of(
            ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
        MethodHandle strlen = linker.downcallHandle(strlenAddress, strlenDescriptor);
        
        // Allocate native memory for string
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment cString = arena.allocateUtf8String("Hello Panama!");
            
            // Call native strlen function
            long length = (long) strlen.invoke(cString);
            System.out.println("String length: " + length);
        }
    }
    
    // Vector API example (Panama sub-project)
    public void vectorExample() {
        var species = FloatVector.SPECIES_256;
        float[] a = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f};
        float[] b = {2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f};
        float[] result = new float[8];
        
        // SIMD vector operations
        var va = FloatVector.fromArray(species, a, 0);
        var vb = FloatVector.fromArray(species, b, 0);
        var vr = va.mul(vb); // Parallel multiplication
        vr.intoArray(result, 0);
    }
}
```

---

### Question 409: What is Project Valhalla?

**Answer (35 seconds):**
* OpenJDK project introducing value types and specialized generics
* **Value Classes**: Objects without identity, stored inline
* **Primitive Classes**: User-defined primitives like int, double
* **Specialized Generics**: Generic types over primitives without boxing
* **Performance**: Eliminates object overhead and indirection
* **Memory Efficiency**: Compact memory layout for data structures
* **Backward Compatible**: Existing code continues to work

```java
// Project Valhalla - Value Classes example (future syntax)
public value class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int x() { return x; }
    public int y() { return y; }
    
    // Value classes are immutable and have no identity
    // Stored inline in arrays and collections
}

public class ValhallaBenefits {
    
    public static void main(String[] args) {
        // Array of value classes - stored inline, no object headers
        Point[] points = new Point[1000];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(i, i * 2); // No heap allocation
        }
        
        // Specialized generics - no boxing
        List<int> numbers = List.of(1, 2, 3, 4, 5); // Future: primitive in generics
        
        // Current workaround vs future
        List<Integer> currentWay = List.of(1, 2, 3); // Boxing overhead
        // List<int> futureWay = List.of(1, 2, 3);   // No boxing
    }
}

// Benefits comparison
/*
Current (Object-based):
- Point[] array: 1000 objects + 1000 headers = ~32KB
- List<Integer>: Boxing overhead, pointer indirection

Future (Value-based):  
- Point[] array: Inline storage = ~8KB (75% less memory)
- List<int>: No boxing, direct primitive storage
- Better cache locality and performance
*/
```

---

### Question 410: What is Project Amber?

**Answer (35 seconds):**
* OpenJDK project delivering small, productivity-focused language features
* **Local Variable Type Inference**: var keyword for cleaner code
* **Switch Expressions**: Enhanced switch with return values
* **Text Blocks**: Multi-line string literals
* **Pattern Matching**: Destructuring and type testing
* **Records**: Compact data classes
* **Sealed Classes**: Restricted inheritance hierarchies

```java
// Project Amber features
public class AmberFeatures {
    
    // Records (delivered in Java 14)
    public record Person(String name, int age) {
        // Automatically generates constructor, getters, equals, hashCode, toString
    }
    
    // Sealed classes (delivered in Java 17)
    public sealed interface Shape permits Circle, Rectangle, Triangle {
        double area();
    }
    
    public record Circle(double radius) implements Shape {
        public double area() { return Math.PI * radius * radius; }
    }
    
    public static void main(String[] args) {
        // var keyword (delivered in Java 10)
        var message = "Hello Amber!";
        var numbers = List.of(1, 2, 3, 4, 5);
        
        // Text blocks (delivered in Java 15)
        var json = """
            {
                "name": "John",
                "age": 30,
                "city": "New York"
            }
            """;
        
        // Switch expressions (delivered in Java 14)
        var dayType = switch (java.time.LocalDate.now().getDayOfWeek()) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
        
        // Pattern matching for instanceof (delivered in Java 16)
        Object obj = "Hello";
        if (obj instanceof String s) {
            System.out.println("String length: " + s.length());
        }
        
        // Pattern matching for switch (preview)
        var result = switch (obj) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case null -> "null value";
            default -> "Unknown type";
        };
    }
}
```

---

### Question 411: What is WebAssembly with Java?

**Answer (30 seconds):**
* Technology to run Java applications in web browsers via WebAssembly
* **Browser Execution**: Java code runs directly in browser without plugins
* **Performance**: Near-native performance in web environments
* **Portability**: Same Java code runs on server and client
* **Tools**: TeaVM, CheerpJ, GraalVM compile Java to WebAssembly
* **Use Cases**: Web applications, games, scientific computing
* **Limitations**: Limited Java API support, larger bundle sizes

```java
// Java code that can be compiled to WebAssembly
public class WebAssemblyExample {
    
    // Simple calculation that can run in browser
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    // Game logic example
    public static class GameEngine {
        private int score = 0;
        private double playerX = 0;
        private double playerY = 0;
        
        public void updatePlayer(double deltaX, double deltaY) {
            playerX += deltaX;
            playerY += deltaY;
        }
        
        public int getScore() { return score; }
        public double getPlayerX() { return playerX; }
        public double getPlayerY() { return playerY; }
    }
    
    // Export methods for JavaScript interaction
    public static void main(String[] args) {
        // This main method won't be used in WebAssembly
        // Instead, individual methods are exported
    }
}

// Compilation to WebAssembly
/*
# Using TeaVM
mvn clean compile exec:java

# Using GraalVM
native-image --target=wasm MyJavaClass

# Result: .wasm file that can be loaded in browser
# JavaScript can call Java methods:
# 
# const wasmModule = await WebAssembly.instantiateStreaming(fetch('app.wasm'));
# const distance = wasmModule.instance.exports.calculateDistance(0, 0, 3, 4);
*/
```

---

### Question 412: What is cloud native Java?

**Answer (35 seconds):**
* Java applications designed specifically for cloud environments
* **Microservices**: Decomposed into small, independent services
* **Containers**: Packaged in Docker containers for portability
* **Orchestration**: Managed by Kubernetes for scaling and resilience
* **Fast Startup**: Optimized for quick container startup times
* **Low Memory**: Efficient memory usage for cost optimization
* **Observability**: Built-in monitoring, logging, and tracing
* **Frameworks**: Spring Boot, Quarkus, Micronaut for cloud-native development

```java
// Cloud-native Java application example
@SpringBootApplication
@EnableEurekaClient
public class CloudNativeApp {
    
    public static void main(String[] args) {
        SpringApplication.run(CloudNativeApp.class, args);
    }
}

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired private UserService userService;
    
    // Health check endpoint for Kubernetes
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("UP");
    }
    
    // Metrics endpoint for monitoring
    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Object>> metrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("users.count", userService.getUserCount());
        metrics.put("memory.used", Runtime.getRuntime().totalMemory());
        return ResponseEntity.ok(metrics);
    }
    
    @GetMapping("/users/{id}")
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    public User fallbackUser(Long id, Exception ex) {
        return new User(id, "Default User", "default@email.com");
    }
}

// Dockerfile for containerization
/*
FROM openjdk:17-jre-slim
COPY target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
*/

// Kubernetes deployment
/*
apiVersion: apps/v1
kind: Deployment
metadata:
  name: cloud-native-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: cloud-native-app
  template:
    spec:
      containers:
      - name: app
        image: myapp:latest
        ports:
        - containerPort: 8080
        livenessProbe:
          httpGet:
            path: /api/health
            port: 8080
        readinessProbe:
          httpGet:
            path: /api/health
            port: 8080
*/
```

---

### Question 413: What is serverless Java?

**Answer (35 seconds):**
* Running Java applications without managing servers or infrastructure
* **Function as a Service**: Deploy individual functions that scale automatically
* **Event-Driven**: Functions triggered by events (HTTP, database, queue)
* **Pay-per-Use**: Only pay for actual execution time
* **Auto-Scaling**: Automatically scales from zero to thousands of instances
* **Cold Start**: Challenge with Java's startup time
* **Platforms**: AWS Lambda, Azure Functions, Google Cloud Functions
* **Frameworks**: Spring Cloud Function, Quarkus, Micronaut for serverless

```java
// AWS Lambda function example
public class ServerlessHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        
        // Extract data from request
        String body = input.getBody();
        Map<String, String> headers = input.getHeaders();
        
        // Business logic
        String result = processRequest(body);
        
        // Return response
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        response.setBody(result);
        response.setHeaders(Map.of("Content-Type", "application/json"));
        
        return response;
    }
    
    private String processRequest(String input) {
        // Process the request
        return "{\"message\": \"Processed: " + input + "\"}";
    }
}

// Spring Cloud Function example
@Component
public class UserFunctions {
    
    @Bean
    public Function<User, User> processUser() {
        return user -> {
            // Transform user data
            user.setProcessedAt(Instant.now());
            return user;
        };
    }
    
    @Bean
    public Consumer<String> logMessage() {
        return message -> {
            System.out.println("Received: " + message);
        };
    }
    
    @Bean
    public Supplier<String> generateId() {
        return () -> UUID.randomUUID().toString();
    }
}

// Quarkus native serverless (faster cold start)
@Path("/hello")
public class GreetingResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus serverless!";
    }
}

// Deployment configuration
/*
# AWS SAM template
Resources:
  MyFunction:
    Type: AWS::Serverless::Function
    Properties:
      CodeUri: target/function.jar
      Handler: com.example.ServerlessHandler::handleRequest
      Runtime: java17
      MemorySize: 512
      Timeout: 30
      Events:
        Api:
          Type: Api
          Properties:
            Path: /process
            Method: post
*/
```

---

### Question 414: What is edge computing with Java?

**Answer (35 seconds):**
* Running Java applications closer to end users for reduced latency
* **Edge Locations**: Data centers near users (CDN nodes, cell towers)
* **Low Latency**: Millisecond response times for real-time applications
* **Bandwidth Optimization**: Process data locally, send only results
* **Offline Capability**: Continue working when disconnected from cloud
* **IoT Integration**: Process sensor data at the edge
* **Challenges**: Limited resources, intermittent connectivity
* **Solutions**: Lightweight Java runtimes, GraalVM native images

```java
// Edge computing Java application
@SpringBootApplication
public class EdgeApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class, args);
    }
}

@RestController
public class EdgeController {
    
    @Autowired private LocalDataProcessor processor;
    @Autowired private CloudSyncService cloudSync;
    
    // Process data locally at edge
    @PostMapping("/process")
    public ResponseEntity<ProcessResult> processData(@RequestBody SensorData data) {
        
        // Process immediately at edge for low latency
        ProcessResult result = processor.processLocally(data);
        
        // Async sync to cloud when connectivity available
        cloudSync.syncWhenAvailable(data, result);
        
        return ResponseEntity.ok(result);
    }
    
    // Health check for edge node
    @GetMapping("/health")
    public ResponseEntity<EdgeHealth> health() {
        EdgeHealth health = new EdgeHealth();
        health.setStatus("UP");
        health.setConnectivity(cloudSync.isCloudReachable());
        health.setLocalStorage(processor.getStorageStatus());
        return ResponseEntity.ok(health);
    }
}

@Service
public class LocalDataProcessor {
    
    private final Map<String, Object> localCache = new ConcurrentHashMap<>();
    
    public ProcessResult processLocally(SensorData data) {
        // Process data without cloud dependency
        double processedValue = applyLocalAlgorithm(data.getValue());
        
        // Store locally for offline capability
        localCache.put(data.getId(), processedValue);
        
        // Return immediate result
        return new ProcessResult(data.getId(), processedValue, Instant.now());
    }
    
    private double applyLocalAlgorithm(double input) {
        // Lightweight processing suitable for edge
        return input * 1.5 + Math.sin(input);
    }
    
    public String getStorageStatus() {
        return "Used: " + localCache.size() + " entries";
    }
}

// Docker configuration for edge deployment
/*
FROM openjdk:17-jre-alpine
RUN apk add --no-cache curl
COPY target/edge-app.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1
ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app.jar"]
*/
```

# 🔹 Integration with Modern Technologies

### Question 415: What is artificial intelligence in Java?

**Answer (35 seconds):**
* Using Java for AI and machine learning applications
* **Libraries**: Deeplearning4j, Weka, MOA for ML algorithms
* **Integration**: Call Python AI models via JNI or REST APIs
* **Big Data**: Spark, Hadoop for large-scale data processing
* **Neural Networks**: Deep learning frameworks in Java ecosystem
* **NLP**: Natural language processing with OpenNLP, Stanford CoreNLP
* **Computer Vision**: ImageJ, OpenCV Java bindings
* **Production**: Java's enterprise features for AI model deployment

```java
// AI/ML example with Deeplearning4j
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

public class JavaAIExample {
    
    public static void main(String[] args) {
        // Create neural network configuration
        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
            .list()
            .layer(new DenseLayer.Builder()
                .nIn(4)  // Input features
                .nOut(10) // Hidden neurons
                .activation(Activation.RELU)
                .build())
            .layer(new OutputLayer.Builder()
                .nIn(10)
                .nOut(3)  // Output classes
                .activation(Activation.SOFTMAX)
                .build())
            .build();
        
        // Create and initialize network
        MultiLayerNetwork model = new MultiLayerNetwork(config);
        model.init();
        
        // Training data (features and labels)
        INDArray features = Nd4j.rand(100, 4); // 100 samples, 4 features
        INDArray labels = Nd4j.rand(100, 3);   // 100 samples, 3 classes
        DataSet dataSet = new DataSet(features, labels);
        
        // Train the model
        for (int i = 0; i < 1000; i++) {
            model.fit(dataSet);
        }
        
        // Make predictions
        INDArray testInput = Nd4j.rand(1, 4);
        INDArray prediction = model.output(testInput);
        System.out.println("Prediction: " + prediction);
    }
}

// NLP example with Stanford CoreNLP
public class NLPExample {
    
    public void analyzeText(String text) {
        // Initialize NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        
        // Create document
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        
        // Extract information
        for (CoreSentence sentence : document.sentences()) {
            System.out.println("Sentiment: " + sentence.sentiment());
            
            for (CoreEntityMention entity : sentence.entityMentions()) {
                System.out.println("Entity: " + entity.text() + " (" + entity.entityType() + ")");
            }
        }
    }
}
```

---

### Question 416: What is machine learning with Java?

**Answer (35 seconds):**
* Implementing ML algorithms and models using Java ecosystem
* **Weka**: Comprehensive ML library with GUI and API
* **Deeplearning4j**: Deep learning for Java with GPU support
* **Smile**: Statistical machine learning library
* **Apache Spark MLlib**: Distributed machine learning
* **MOA**: Massive online analysis for streaming data
* **Integration**: TensorFlow Java, ONNX Runtime for Java
* **Production**: Enterprise-grade ML model serving and monitoring

```java
// Machine Learning with Weka
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaMLExample {
    
    public static void main(String[] args) throws Exception {
        // Load dataset
        DataSource source = new DataSource("data/iris.arff");
        Instances data = source.getDataSet();
        
        // Set class attribute (last attribute)
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        
        // Split data into training and testing
        int trainSize = (int) Math.round(data.numInstances() * 0.8);
        Instances trainData = new Instances(data, 0, trainSize);
        Instances testData = new Instances(data, trainSize, data.numInstances() - trainSize);
        
        // Create and train classifier
        J48 classifier = new J48(); // Decision tree
        classifier.buildClassifier(trainData);
        
        // Evaluate model
        Evaluation eval = new Evaluation(trainData);
        eval.evaluateModel(classifier, testData);
        
        System.out.println("Accuracy: " + eval.pctCorrect() + "%");
        System.out.println(eval.toSummaryString());
    }
}

// Apache Spark MLlib example
public class SparkMLExample {
    
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
            .appName("ML Example")
            .master("local[*]")
            .getOrCreate();
        
        // Load data
        Dataset<Row> data = spark.read()
            .option("header", "true")
            .option("inferSchema", "true")
            .csv("data/housing.csv");
        
        // Feature engineering
        VectorAssembler assembler = new VectorAssembler()
            .setInputCols(new String[]{"bedrooms", "bathrooms", "sqft"})
            .setOutputCol("features");
        
        Dataset<Row> featureData = assembler.transform(data);
        
        // Split data
        Dataset<Row>[] splits = featureData.randomSplit(new double[]{0.8, 0.2});
        Dataset<Row> trainData = splits[0];
        Dataset<Row> testData = splits[1];
        
        // Create and train model
        LinearRegression lr = new LinearRegression()
            .setFeaturesCol("features")
            .setLabelCol("price");
        
        LinearRegressionModel model = lr.fit(trainData);
        
        // Make predictions
        Dataset<Row> predictions = model.transform(testData);
        predictions.select("features", "price", "prediction").show();
        
        // Evaluate model
        RegressionEvaluator evaluator = new RegressionEvaluator()
            .setLabelCol("price")
            .setPredictionCol("prediction")
            .setMetricName("rmse");
        
        double rmse = evaluator.evaluate(predictions);
        System.out.println("RMSE: " + rmse);
        
        spark.stop();
    }
}
```

---

### Question 417: What is blockchain development with Java?

**Answer (35 seconds):**
* Building blockchain applications and smart contracts using Java
* **Web3j**: Java library for Ethereum blockchain interaction
* **Hyperledger Fabric**: Enterprise blockchain with Java SDK
* **Corda**: Blockchain platform built in Kotlin/Java
* **Smart Contracts**: Deploy and interact with blockchain contracts
* **DApps**: Decentralized applications with Java backends
* **Cryptocurrency**: Bitcoin and Ethereum integration
* **Enterprise**: Supply chain, finance, identity management solutions

```java
// Ethereum blockchain interaction with Web3j
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

public class BlockchainExample {
    
    private Web3j web3j;
    
    public BlockchainExample() {
        // Connect to Ethereum node
        web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_PROJECT_ID"));
    }
    
    public void getAccountBalance(String address) throws Exception {
        // Get account balance
        EthGetBalance ethGetBalance = web3j
            .ethGetBalance(address, DefaultBlockParameterName.LATEST)
            .send();
        
        BigInteger balance = ethGetBalance.getBalance();
        BigDecimal etherBalance = Convert.fromWei(balance.toString(), Convert.Unit.ETHER);
        
        System.out.println("Balance: " + etherBalance + " ETH");
    }
    
    public void deployContract() throws Exception {
        // Load credentials
        Credentials credentials = WalletUtils.loadCredentials("password", "wallet.json");
        
        // Deploy smart contract
        MyContract contract = MyContract.deploy(
            web3j, 
            credentials, 
            new DefaultGasProvider()
        ).send();
        
        System.out.println("Contract deployed at: " + contract.getContractAddress());
        
        // Interact with contract
        TransactionReceipt receipt = contract.setValue(BigInteger.valueOf(42)).send();
        BigInteger value = contract.getValue().send();
        System.out.println("Contract value: " + value);
    }
}

// Hyperledger Fabric example
public class FabricExample {
    
    public void initializeFabric() throws Exception {
        // Create network gateway
        Gateway.Builder builder = Gateway.createBuilder();
        
        // Load identity
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        
        // Connect to network
        try (Gateway gateway = builder.identity(wallet, "user1")
                .networkConfig(Paths.get("connection.yaml"))
                .connect()) {
            
            // Get network and contract
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("mycontract");
            
            // Submit transaction
            byte[] result = contract.submitTransaction("createAsset", "asset1", "blue", "35", "tom", "1000");
            System.out.println("Transaction result: " + new String(result));
            
            // Query ledger
            byte[] queryResult = contract.evaluateTransaction("queryAsset", "asset1");
            System.out.println("Asset: " + new String(queryResult));
        }
    }
}

// Cryptocurrency price tracking
public class CryptoTracker {
    
    public void trackBitcoinPrice() {
        // Connect to cryptocurrency API
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        // Parse JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        
        String price = root.path("bpi").path("USD").path("rate").asText();
        System.out.println("Bitcoin price: " + price);
    }
}
```

---

### Question 418: What is IoT development with Java?

**Answer (35 seconds):**
* Building Internet of Things applications using Java ecosystem
* **Device Programming**: Java ME, Android Things for IoT devices
* **Edge Computing**: Process sensor data locally with Java
* **MQTT Integration**: Message queuing for IoT communication
* **Cloud Connectivity**: Connect devices to cloud platforms
* **Data Processing**: Stream processing with Apache Kafka, Storm
* **Protocols**: HTTP, CoAP, MQTT, WebSocket support
* **Frameworks**: Eclipse IoT, Apache Camel for IoT integration

```java
// IoT device simulation with MQTT
import org.eclipse.paho.client.mqttv3.*;

public class IoTDeviceExample {
    
    private MqttClient mqttClient;
    private final String BROKER_URL = "tcp://iot.eclipse.org:1883";
    private final String CLIENT_ID = "JavaIoTDevice";
    
    public void connectToMqttBroker() throws Exception {
        mqttClient = new MqttClient(BROKER_URL, CLIENT_ID);
        
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        
        mqttClient.connect(options);
        System.out.println("Connected to MQTT broker");
        
        // Subscribe to commands
        mqttClient.subscribe("device/commands", this::handleCommand);
    }
    
    public void publishSensorData() throws Exception {
        // Simulate sensor readings
        SensorData data = new SensorData();
        data.setTemperature(25.5 + Math.random() * 10);
        data.setHumidity(60 + Math.random() * 20);
        data.setTimestamp(Instant.now());
        
        // Convert to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        
        // Publish to MQTT topic
        MqttMessage message = new MqttMessage(json.getBytes());
        message.setQos(1);
        mqttClient.publish("sensors/temperature", message);
        
        System.out.println("Published: " + json);
    }
    
    private void handleCommand(String topic, MqttMessage message) {
        String command = new String(message.getPayload());
        System.out.println("Received command: " + command);
        
        // Process device commands
        switch (command) {
            case "START_MONITORING":
                startSensorMonitoring();
                break;
            case "STOP_MONITORING":
                stopSensorMonitoring();
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }
}

// IoT data processing with Apache Kafka
@Component
public class IoTDataProcessor {
    
    @KafkaListener(topics = "sensor-data")
    public void processSensorData(String sensorDataJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SensorData data = mapper.readValue(sensorDataJson, SensorData.class);
            
            // Process sensor data
            if (data.getTemperature() > 30) {
                sendAlert("High temperature detected: " + data.getTemperature());
            }
            
            // Store in database
            sensorDataRepository.save(data);
            
            // Send to analytics
            analyticsService.processData(data);
            
        } catch (Exception e) {
            logger.error("Error processing sensor data", e);
        }
    }
    
    private void sendAlert(String message) {
        // Send alert via email, SMS, or push notification
        alertService.sendAlert(message);
    }
}

// IoT gateway with Apache Camel
public class IoTGateway extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        // Route from MQTT to HTTP REST API
        from("mqtt:sensor-data?host=tcp://localhost:1883")
            .log("Received sensor data: ${body}")
            .marshal().json()
            .to("http://cloud-api.example.com/sensors");
        
        // Route from file system to MQTT (for local sensors)
        from("file:sensors?noop=true")
            .log("Processing file: ${header.CamelFileName}")
            .to("mqtt:file-data?host=tcp://localhost:1883");
        
        // Route for device management
        from("rest:post:/devices/{deviceId}/command")
            .log("Command for device ${header.deviceId}: ${body}")
            .to("mqtt:device-commands?host=tcp://localhost:1883");
    }
}

// IoT device data model
public class SensorData {
    private String deviceId;
    private double temperature;
    private double humidity;
    private double pressure;
    private Instant timestamp;
    private Location location;
    
    // Getters and setters
}
```