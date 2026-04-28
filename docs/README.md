# Assignment 3: Sorting and Searching Algorithm Analysis

## Project Overview
This project implements and compares three algorithms:
1) Bubble Sort (Basic Sorting)
2) Quick Sort (Advanced Sorting)
3) Binary Search (Searching)

Goal: analyze their performance on different array sizes and input types using execution time

---

## Algorithms Description

### Bubble Sort
Bubble Sort compares two adjacent elements and swaps them if needed

**Time Complexity:**
- Best O(n)
- Average O(n²)
- Worst O(n²)

---

### Quick Sort
Quick Sort divides the array into parts using a pivot and sorts them by recursion

**Time Complexity:**
- Best O(n log n)
- Average O(n log n)
- Worst O(n²)

---

### Binary Search
Binary Search finds an element by repeatedly dividing the search space in half.

**Time Complexity:**
- Best O(1)
- Average O(log n)
- Worst O(log n)

---

## Experimental Results

### Array sizes tested:
- 10 elements
- 100 elements
- 1000+ elements

### Input types:
- Random arrays
- Sorted arrays

---

## Results


| Size | Type   | Bubble (ns) | Quick (ns) | Search (ns) |
|------|--------|------------|-----------|------------|
| 100  | Random | 222900     | 37500     | 1733       |
| 100  | Sorted | 139700     | 223700    | 0*         |
| 1000 | Random | 5247000    | 157500    | 1933       |
| 1000 | Sorted | 901000     | 2302000   | 0*         |
| 5000 | Random | 25450000   | 514300    | 2000       |
| 5000 | Sorted | 4832000    | 13180000  | 0*         |

---

## Analysis

- Quick Sort is significantly faster than Bubble Sort on large arrays
- Bubble Sort performance degrades heavily as array size increases
- Sorted input improves Bubble Sort performance but worsens Quick Sort in some cases
- Binary Search is extremely fast compared to sorting algorithms

Results clearly match Big-O expectations:
  
1) Bubble Sort O(n²)
2) Quick Sort O(n log n)
3) Binary Search O(log n)
---

## Reflection

This project helped me understand how algorithm efficiency works in practice. Bubble Sort is easy to implement but inefficient for large datasets, while Quick Sort performs significantly better. One challenge was organizing the project structure and running correct experiments. Also I learned that input type (sorted and random) strongly affects performance

---

## Conclusion

1) Quick Sort is the best choice for large datasets
2) Bubble Sort is only useful for small or already sorted arrays
3) Binary Search is very efficient, but it requires sorted data.

---

### Run 1
![Run1](docs/screenshots/run1.png)

### Run 2
![Run2](docs/screenshots/run2.png)

### Run 3
![Run3](docs/screenshots/run3.png)