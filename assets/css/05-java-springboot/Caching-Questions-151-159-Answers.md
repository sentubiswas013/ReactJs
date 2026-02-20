# ✅ 12) Caching Strategies (Redis / Distributed Cache)

## 151. What is caching and why is it important?

**Answer:** Caching stores frequently accessed data in fast-access memory to reduce database load and improve response time.

**Example:**
```java
// Without cache: DB call every time
User user = userRepository.findById(1L); // 100ms

// With cache: DB call once, then memory
User user = cache.get("user:1"); // 1ms
```

---

## 152. Explain cache-aside, write-through, write-behind patterns.

**Answer:**
- **Cache-Aside**: App checks cache first, loads from DB on miss, then updates cache
- **Write-Through**: Write to cache and DB simultaneously
- **Write-Behind**: Write to cache immediately, DB asynchronously

**Example:**
```java
// Cache-Aside
public User getUser(Long id) {
    User user = cache.get("user:" + id);
    if (user == null) {
        user = db.findById(id);
        cache.put("user:" + id, user);
    }
    return user;
}

// Write-Through
public void saveUser(User user) {
    db.save(user);
    cache.put("user:" + user.getId(), user);
}
```

---

## 153. Local cache vs distributed cache - when to use which?

**Answer:**
- **Local Cache**: In-memory (Caffeine, Guava), single instance, fast but not shared
- **Distributed Cache**: Shared across instances (Redis), consistent but network latency

**Example:**
```java
// Local Cache - for static data
@Cacheable(cacheNames = "countries")
public List<String> getCountries() { }

// Distributed Cache - for user sessions
@Cacheable(cacheNames = "userSessions", cacheManager = "redisCacheManager")
public Session getUserSession(String token) { }
```

---

## 154. How does Redis work? What data structures does it support?

**Answer:** Redis is an in-memory key-value store. Supports: String, List, Set, Sorted Set, Hash, Bitmap, HyperLogLog, Stream.

**Example:**
```java
// String
redisTemplate.opsForValue().set("user:1", "John");

// Hash
redisTemplate.opsForHash().put("user:1", "name", "John");

// List
redisTemplate.opsForList().rightPush("queue", "task1");

// Set
redisTemplate.opsForSet().add("tags", "java", "spring");
```

---

## 155. What is cache eviction policy (LRU, LFU)?

**Answer:**
- **LRU (Least Recently Used)**: Removes least recently accessed items
- **LFU (Least Frequently Used)**: Removes least frequently accessed items

**Example:**
```java
@Bean
public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterAccess(10, TimeUnit.MINUTES)); // LRU-like
    return cacheManager;
}
```

---

## 156. How do you implement caching in Spring Boot (`@Cacheable`, `@CachePut`, `@CacheEvict`)?

**Answer:**
- **@Cacheable**: Caches method result
- **@CachePut**: Updates cache
- **@CacheEvict**: Removes from cache

**Example:**
```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @CachePut(value = "users", key = "#user.id")
    public User update(User user) {
        return userRepository.save(user);
    }
    
    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
```

---

## 157. What is cache stampede? How to prevent it?

**Answer:** Cache stampede occurs when many requests simultaneously try to regenerate an expired cache entry, overwhelming the database.

**Prevention:** Use locking, stale-while-revalidate, or probabilistic early expiration.

**Example:**
```java
// Using synchronized to prevent stampede
private final Object lock = new Object();

public User getUser(Long id) {
    User user = cache.get("user:" + id);
    if (user == null) {
        synchronized (lock) {
            user = cache.get("user:" + id); // Double-check
            if (user == null) {
                user = db.findById(id);
                cache.put("user:" + id, user);
            }
        }
    }
    return user;
}

// Or use @Cacheable with sync
@Cacheable(value = "users", key = "#id", sync = true)
public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
}
```

---

## 158. How do you handle cache invalidation?

**Answer:** Invalidate cache on data updates using TTL (Time-To-Live), event-based invalidation, or manual eviction.

**Example:**
```java
@Service
public class UserService {
    
    @Autowired
    private CacheManager cacheManager;
    
    // TTL-based
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // Event-based invalidation
    @CacheEvict(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    // Manual invalidation
    public void clearUserCache(Long userId) {
        Cache cache = cacheManager.getCache("users");
        if (cache != null) {
            cache.evict(userId);
        }
    }
    
    // Clear all
    @CacheEvict(value = "users", allEntries = true)
    public void clearAllUsers() { }
}
```

---

## 159. What is Redis Cluster vs Redis Sentinel?

**Answer:**
- **Redis Sentinel**: Provides high availability through monitoring, automatic failover, and master-slave replication
- **Redis Cluster**: Provides horizontal scaling with data sharding across multiple nodes

**Example:**
```java
// Redis Sentinel Configuration
@Configuration
public class RedisSentinelConfig {
    @Bean
    public RedisConnectionFactory sentinelConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = 
            new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
    }
}

// Redis Cluster Configuration
@Configuration
public class RedisClusterConfig {
    @Bean
    public RedisConnectionFactory clusterConnectionFactory() {
        RedisClusterConfiguration clusterConfig = 
            new RedisClusterConfiguration()
                .clusterNode("127.0.0.1", 7000)
                .clusterNode("127.0.0.1", 7001)
                .clusterNode("127.0.0.1", 7002);
        return new LettuceConnectionFactory(clusterConfig);
    }
}
```

