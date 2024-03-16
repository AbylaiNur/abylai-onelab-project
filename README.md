## Prerequisites

Before you start the application, make sure you have Apache Kafka and ZooKeeper installed and configured on your machine. If not, please follow the installation instructions for your operating system on the [official Apache Kafka website](https://kafka.apache.org/quickstart).

## Starting ZooKeeper and Kafka

Follow these steps to start ZooKeeper and Kafka in Linux or WSL:

### Start ZooKeeper

1. Open a terminal window.
2. Navigate to the directory where Kafka is installed.
3. Run the following command to start ZooKeeper:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Start Kafka
1. Open a new terminal window.
2. Navigate to the directory where Kafka is installed.
3. Run the following command to start the Kafka server:

```bash
bin/kafka-server-start.sh config/server.properties
```
There may be a problem with the default settings for the Kafka server. If you encounter an error, you may need to change in the `config/server.properties` file from `PLAINTEXT://:9092` to `PLAINTEXT://localhost:9092`.

Before running, you may need to configure the Kafka bootstrap server in the application.properties file if it's not running on the default settings (localhost:9092).
