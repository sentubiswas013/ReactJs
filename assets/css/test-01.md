# Java Interview Questions - Organized by Category

## Java Platform

1. Why is Java so popular?
2. What is platform independence?
3. What is bytecode?
4. Compare JDK vs JVM vs JRE
5. What are the important differences between C++ and Java?
6. What is the role of a classloader in Java?

## Wrapper Classes

7. What are Wrapper classes?
8. Why do we need Wrapper classes in Java?
9. What are the different ways of creating Wrapper class instances?
10. What are the differences in the two ways of creating Wrapper classes?
11. What is auto boxing?
12. What are the advantages of auto boxing?
13. What is casting?
14. What is implicit casting?
15. What is explicit casting?

## Strings

16. Are all Strings immutable?
17. Where are String values stored in memory?
18. Why should you be careful about the String concatenation (`+`) operator in loops?
19. How do you solve the above problem?
20. What are differences between String and StringBuffer?
21. What are differences between StringBuilder and StringBuffer?
22. Can you give examples of different utility methods in the String class?

## Object Oriented Programming Basics

23. What is a class?
24. What is an object?
25. What is the state of an object?
26. What is behavior of an object?
27. What is the super class of every class in Java?
28. Explain about the `toString` method?
29. What is the use of the `equals` method in Java?
30. What are the important things to consider when implementing the `equals` method?
31. What is the `hashCode` method used for in Java?
32. Explain inheritance with examples.
33. What is method overloading?
34. What is method overriding?
35. Can a superclass reference variable hold an object of a subclass?
36. Is multiple inheritance allowed in Java?
37. What is an interface?
38. How do you define an interface?
39. How do you implement an interface?
40. Can you explain a few tricky things about interfaces?
41. Can you extend an interface?
42. Can a class extend multiple interfaces?
43. What is an abstract class?
44. When do you use an abstract class?
45. How do you define an abstract method?
46. Compare abstract class vs interface?

## Constructors

47. What is a constructor?
48. What is a default constructor?
49. How do you call a superclass constructor from a constructor?
50. What is the use of `this()`?
51. Can a constructor be called directly from a method?
52. Is a superclass constructor called even when there is no explicit call from a subclass constructor?

## Advanced Object Oriented Concepts

53. What is polymorphism?
54. What is the use of `instanceof` operator in Java?
55. What is coupling?
56. What is cohesion?
57. What is encapsulation?
58. What is an inner class?
59. What is a static inner class?
60. Can you create an inner class inside a method?
61. What is an anonymous class?

## Modifiers

62. What is the default class modifier?
63. What is the private access modifier?
64. What is the default or package access modifier?
65. What is the protected access modifier?
66. What is the public access modifier?
67. What access types of variables can be accessed from a class in the same package?
68. What access types of variables can be accessed from a class in a different package?
69. What access types of variables can be accessed from a subclass in the same package?
70. What access types of variables can be accessed from a subclass in a different package?
71. What is the use of a final modifier on a class?
72. What is the use of a final modifier on a method?
73. What is a final variable?
74. What is a final argument?
75. What happens when a variable is marked as volatile?
76. What is a static variable?

## Conditions & Loops

77. Why should you always use blocks around an if statement?
78. Should `default` be the last case in a switch statement?
79. Can a switch statement be used around a String?
80. What is an enhanced for loop?

## Exception Handling

81. Why is exception handling important?
82. What design pattern is used to implement exception handling features in most languages?
83. What is the need for a `finally` block?
84. In what scenarios is code in `finally` not executed?
85. Will `finally` be executed in the program below?
86. Is `try` without a `catch` allowed?
87. Is `try` without `catch` and `finally` allowed?
88. Can you explain the hierarchy of exception handling classes?
89. What is the difference between error and exception?
90. What is the difference between checked exceptions and unchecked exceptions?
91. How do you throw an exception from a method?
92. What happens when you throw a checked exception from a method?
93. What are the options you have to eliminate compilation errors when handling checked exceptions?
94. How do you create a custom exception?
95. How do you handle multiple exception types with the same exception handling block?
96. Can you explain about try-with-resources?
97. How does try-with-resources work?
98. Can you explain a few exception handling best practices?

## Miscellaneous Topics

99. What are the default values in an array?
100. How do you loop around an array using an enhanced for loop?
101. How do you print the content of an array?
102. How do you compare two arrays?
103. What is an enum?
104. Can you use a switch statement around an enum?
105. What are variable arguments or varargs?
106. What are asserts used for?
107. When should asserts be used?
108. What is garbage collection?
109. Can you explain garbage collection with an example?
110. When is garbage collection run?
111. What are best practices on garbage collection?
112. What are initialization blocks?
113. What is a static initializer?
114. What is an instance initializer block?
115. What is tokenizing?
116. Can you give an example of tokenizing?
117. What is serialization?
118. How do you serialize an object using the `Serializable` interface?
119. How do you de-serialize in Java?
120. What do you do if only parts of the object have to be serialized?
121. How do you serialize a hierarchy of objects?
122. Are the constructors in an object invoked when it is de-serialized?
123. Are the values of static variables stored when an object is serialized?

## Collections

124. Why do we need collections in Java?
125. What are the important interfaces in the collection hierarchy?
126. What are the important methods that are declared in the collection interface?
127. Can you explain briefly about the List interface?
128. Explain about ArrayList with an example?
129. Can an ArrayList have duplicate elements?
130. How do you iterate around an ArrayList using iterator?
131. How do you sort an ArrayList?
132. How do you sort elements in an ArrayList using the `Comparable` interface?
133. How do you sort elements in an ArrayList using the `Comparator` interface?
134. What is the `Vector` class? How is it different from an `ArrayList`?
135. What is `LinkedList`? What interfaces does it implement? How is it different from an `ArrayList`?
136. Can you briefly explain the `Set` interface?
137. What are the important interfaces related to the `Set` interface?
138. What is the difference between `Set` and `SortedSet` interfaces?
139. Can you give examples of classes that implement the `Set` interface?
140. What is a `HashSet`?
141. What is a `LinkedHashSet`? How is it different from a `HashSet`?
142. What is a `TreeSet`? How is it different from a `HashSet`?
143. Can you give examples of implementations of `NavigableSet`?
144. Explain briefly about the `Queue` interface?
145. What are the important interfaces related to the `Queue` interface?
146. Explain about the `Deque` interface?
147. Explain the `BlockingQueue` interface?
148. What is a `PriorityQueue`?
149. Can you give example implementations of the `BlockingQueue` interface?
150. Can you briefly explain about the `Map` interface?
151. What is the difference between `Map` and `SortedMap`?
152. What is a `HashMap`?
153. What are the different methods in a `HashMap`?
154. What is a `TreeMap`? How is it different from a `HashMap`?
155. Can you give an example of implementation of `NavigableMap` interface?

## Functional Programming (Java 8+)

156. What is the functional interface - Predicate?
157. What is the functional interface - Function?
158. What is a Consumer?
159. Can you give examples of functional interfaces with multiple arguments?

## New Features

160. What are the new features in Java 5?
161. What are the new features in Java 6?
162. What are the new features in Java 7?
163. What are the new features in Java 8?

