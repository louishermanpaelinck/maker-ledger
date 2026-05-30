cd ~/maker-ledger

# Generate Gradle Wrapper
gradle wrapper --gradle-version 8.10

# Or if that fails, use this:
./gradlew wrapper --gradle-version 8.10 || gradle wrapper

echo "✅ Gradle wrapper created. Now build:"
./gradlew build