echo index.html > "benchmark results.txt"
echo. >> "benchmark results.txt"
ab -k -n 100000 -c 2000 http://localhost:5000/ >> "benchmark results.txt"

echo. >> "benchmark results.txt"
echo. >> "benchmark results.txt"
echo /get_brands >> "benchmark results.txt"
echo. >> "benchmark results.txt"
ab -k -n 100000 -c 2000 http://localhost:5000/get_brands >> "benchmark results.txt"