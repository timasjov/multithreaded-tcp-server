import java.util.concurrent.*
import java.util.concurrent.atomic.*
import java.util.Random

THREADS = 100
ITERATIONS = 10

def counter = new AtomicInteger()
def rand = new Random()
def commands = ['ls', 'ps -ef | grep java', 'man java']

def execute_command(command) {
    s = new Socket("localhost", 6789)
    s.withStreams { input, output ->
        output << command + '\n'
        response = input.newReader().readLine()
        println "Result: $response"
    }
    s.close();
}
pool = Executors.newFixedThreadPool(THREADS)
defer = { c -> pool.submit(c as Callable) }

ITERATIONS.times {
    counter.incrementAndGet()
    def command = commands[rand.nextInt(commands.size())]
    defer{ execute_command(command) }
    println "Thread ${counter.get()} started!"
}