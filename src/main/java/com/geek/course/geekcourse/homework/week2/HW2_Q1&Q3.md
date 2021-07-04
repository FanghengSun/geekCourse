# 不同GC分析
## 总结
串行GC和并行GC都会造成STW。串行GC使用单核，并行GC使用多核，提高吞吐量。使用并行GC并不一定会减少GC暂停时间。

CMS对年轻代使用标记复制，对老年代使用标记清除。CMS的GC暂停时间短。在清理老年代的时候会穿插清理年轻代。

G1GC的暂停有young区的也有混合的。并发过程和CMS相似。

堆内存的大小会影响GC的频率。也会影响GC的时间。堆内存比较大时，GC频率降低，但单次GC时间会增加。堆内存设置过小时会引发OOM。
## 串行GC

```bash
java -XX:+PrintGCDetails -Xmx1g -Xms1g -XX:+UseSerialGC com.geek.course.geekcourse.homework.week2.GCLogAnalysis
```

```
正在执行...
[GC (Allocation Failure) [DefNew: 279616K->34944K(314560K), 0.0501537 secs] 279616K->82223K(1013632K), 0.0501827 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314502K->34942K(314560K), 0.0705836 secs] 361781K->162724K(1013632K), 0.0706119 secs] [Times: user=0.04 sys=0.03, real=0.07 secs] 
[GC (Allocation Failure) [DefNew: 314558K->34943K(314560K), 0.0563932 secs] 442340K->245363K(1013632K), 0.0564201 secs] [Times: user=0.03 sys=0.03, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314559K->34941K(314560K), 0.0546729 secs] 524979K->320191K(1013632K), 0.0547044 secs] [Times: user=0.03 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 314557K->34943K(314560K), 0.0544355 secs] 599807K->396713K(1013632K), 0.0544626 secs] [Times: user=0.03 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 314045K->34943K(314560K), 0.0583630 secs] 675815K->475223K(1013632K), 0.0583921 secs] [Times: user=0.04 sys=0.03, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0606228 secs] 754839K->563798K(1013632K), 0.0606486 secs] [Times: user=0.04 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0591783 secs] 843414K->640660K(1013632K), 0.0592198 secs] [Times: user=0.03 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 314559K->314559K(314560K), 0.0000151 secs][Tenured: 605716K->372941K(699072K), 0.0654456 secs] 920276K->372941K(1013632K), [Metaspace: 2596K->2596K(1056768K)], 0.0655018 secs] [Times: user=0.06 sys=0.00, real=0.07 secs] 
执行结束!共生成对象次数:9581
Heap
 def new generation   total 314560K, used 11517K [0x0000000780000000, 0x0000000795550000, 0x0000000795550000)
  eden space 279616K,   4% used [0x0000000780000000, 0x0000000780b3f790, 0x0000000791110000)
  from space 34944K,   0% used [0x0000000791110000, 0x0000000791110000, 0x0000000793330000)
  to   space 34944K,   0% used [0x0000000793330000, 0x0000000793330000, 0x0000000795550000)
 tenured generation   total 699072K, used 372941K [0x0000000795550000, 0x00000007c0000000, 0x00000007c0000000)
   the space 699072K,  53% used [0x0000000795550000, 0x00000007ac1836e8, 0x00000007ac183800, 0x00000007c0000000)
 Metaspace       used 2603K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 279K, capacity 386K, committed 512K, reserved 1048576K

```



## 并行GC

```bash
java -XX:+PrintGCDetails -Xmx1g -Xms1g -XX:+UseParallelGC com.geek.course.geekcourse.homework.week2.GCLogAnalysis
```

```
正在执行...
[GC (Allocation Failure) [PSYoungGen: 262144K->43517K(305664K)] 262144K->83685K(1005056K), 0.0249919 secs] [Times: user=0.05 sys=0.16, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 305661K->43510K(305664K)] 345829K->151607K(1005056K), 0.0386259 secs] [Times: user=0.06 sys=0.25, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 305654K->43509K(305664K)] 413751K->218997K(1005056K), 0.0325907 secs] [Times: user=0.10 sys=0.16, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 305653K->43514K(305664K)] 481141K->295989K(1005056K), 0.0466220 secs] [Times: user=0.14 sys=0.19, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 305658K->43511K(305664K)] 558133K->374463K(1005056K), 0.0394237 secs] [Times: user=0.11 sys=0.20, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 305655K->43508K(160256K)] 636607K->447789K(859648K), 0.0396519 secs] [Times: user=0.10 sys=0.16, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 159926K->83556K(232960K)] 564207K->493870K(932352K), 0.0119829 secs] [Times: user=0.08 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 199717K->105136K(232960K)] 610031K->524327K(932352K), 0.0210775 secs] [Times: user=0.12 sys=0.03, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 221872K->111443K(232960K)] 641063K->549640K(932352K), 0.0308156 secs] [Times: user=0.20 sys=0.05, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 228179K->76403K(232960K)] 666376K->578988K(932352K), 0.0433848 secs] [Times: user=0.08 sys=0.11, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 193139K->33985K(232960K)] 695724K->603954K(932352K), 0.0807090 secs] [Times: user=0.09 sys=0.14, real=0.09 secs] 
[GC (Allocation Failure) [PSYoungGen: 150389K->37476K(232960K)] 720359K->635751K(932352K), 0.0222623 secs] [Times: user=0.05 sys=0.07, real=0.02 secs] 
[Full GC (Ergonomics) [PSYoungGen: 37476K->0K(232960K)] [ParOldGen: 598274K->322758K(699392K)] 635751K->322758K(932352K), [Metaspace: 2596K->2596K(1056768K)], 0.0706729 secs] [Times: user=0.48 sys=0.03, real=0.07 secs] 
执行结束!共生成对象次数:8668
Heap
 PSYoungGen      total 232960K, used 5092K [0x00000007aab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 116736K, 4% used [0x00000007aab00000,0x00000007aaff9308,0x00000007b1d00000)
  from space 116224K, 0% used [0x00000007b8e80000,0x00000007b8e80000,0x00000007c0000000)
  to   space 116224K, 0% used [0x00000007b1d00000,0x00000007b1d00000,0x00000007b8e80000)
 ParOldGen       total 699392K, used 322758K [0x0000000780000000, 0x00000007aab00000, 0x00000007aab00000)
  object space 699392K, 46% used [0x0000000780000000,0x0000000793b31b78,0x00000007aab00000)
 Metaspace       used 2603K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 279K, capacity 386K, committed 512K, reserved 1048576K
```



## CMS

```
java -XX:+PrintGCDetails -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC com.geek.course.geekcourse.homework.week2.GCLogAnalysis
```

```
正在执行...
[GC (Allocation Failure) [ParNew: 279616K->34943K(314560K), 0.0279725 secs] 279616K->87932K(1013632K), 0.0280415 secs] [Times: user=0.07 sys=0.16, real=0.03 secs] 
[GC (Allocation Failure) [ParNew: 314559K->34944K(314560K), 0.0319018 secs] 367548K->166190K(1013632K), 0.0319316 secs] [Times: user=0.08 sys=0.18, real=0.03 secs] 
[GC (Allocation Failure) [ParNew: 314560K->34944K(314560K), 0.0557592 secs] 445806K->245122K(1013632K), 0.0557866 secs] [Times: user=0.52 sys=0.03, real=0.06 secs] 
[GC (Allocation Failure) [ParNew: 314560K->34943K(314560K), 0.0663748 secs] 524738K->319085K(1013632K), 0.0664034 secs] [Times: user=0.59 sys=0.03, real=0.07 secs] 
[GC (Allocation Failure) [ParNew: 314559K->34943K(314560K), 0.0780696 secs] 598701K->396588K(1013632K), 0.0780983 secs] [Times: user=0.68 sys=0.03, real=0.07 secs] 
[GC (CMS Initial Mark) [1 CMS-initial-mark: 361644K(699072K)] 409986K(1013632K), 0.0013389 secs] [Times: user=0.00 sys=0.01, real=0.00 secs] 
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew[CMS-concurrent-abortable-preclean: 0.003/0.105 secs] [Times: user=0.52 sys=0.03, real=0.11 secs] 
: 314559K->34943K(314560K), 0.1233112 secs] 676204K->484658K(1013632K), 0.1233621 secs] [Times: user=1.02 sys=0.05, real=0.12 secs] 
[GC (CMS Final Remark) [YG occupancy: 41230 K (314560 K)][Rescan (parallel) , 0.0012329 secs][weak refs processing, 0.0000085 secs][class unloading, 0.0003144 secs][scrub symbol table, 0.0003070 secs][scrub string table, 0.0001360 secs][1 CMS-remark: 449715K(699072K)] 490945K(1013632K), 0.0020540 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.004/0.004 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [ParNew: 314559K->34942K(314560K), 0.0258190 secs] 627514K->427733K(1013632K), 0.0258546 secs] [Times: user=0.25 sys=0.00, real=0.03 secs] 
[GC (Allocation Failure) [ParNew: 314558K->34943K(314560K), 0.0438858 secs] 707349K->505180K(1013632K), 0.0439169 secs] [Times: user=0.39 sys=0.02, real=0.04 secs] 
[GC (CMS Initial Mark) [1 CMS-initial-mark: 470237K(699072K)] 510948K(1013632K), 0.0002143 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean-start]
执行结束!共生成对象次数:9188
Heap
 par new generation   total 314560K, used 190460K [0x0000000780000000, 0x0000000795550000, 0x0000000795550000)
  eden space 279616K,  55% used [0x0000000780000000, 0x00000007897df348, 0x0000000791110000)
  from space 34944K,  99% used [0x0000000791110000, 0x000000079332fe08, 0x0000000793330000)
  to   space 34944K,   0% used [0x0000000793330000, 0x0000000793330000, 0x0000000795550000)
 concurrent mark-sweep generation total 699072K, used 470237K [0x0000000795550000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2603K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 279K, capacity 386K, committed 512K, reserved 1048576K
```



## G1GC

```
java -XX:+PrintGC -Xmx1g -Xms1g -XX:+UseG1GC com.geek.course.geekcourse.homework.week2.GCLogAnalysis
```

```
正在执行...
[GC pause (G1 Evacuation Pause) (young) 63M->25M(1024M), 0.0074444 secs]
[GC pause (G1 Evacuation Pause) (young) 79M->41M(1024M), 0.0051901 secs]
[GC pause (G1 Evacuation Pause) (young) 94M->57M(1024M), 0.0062521 secs]
[GC pause (G1 Evacuation Pause) (young) 150M->87M(1024M), 0.0118216 secs]
[GC pause (G1 Evacuation Pause) (young) 197M->125M(1024M), 0.0124085 secs]
[GC pause (G1 Evacuation Pause) (young) 343M->188M(1024M), 0.0197992 secs]
[GC pause (G1 Evacuation Pause) (young) 344M->222M(1024M), 0.0156013 secs]
[GC pause (G1 Evacuation Pause) (young) 422M->279M(1024M), 0.0176733 secs]
[GC pause (G1 Evacuation Pause) (young) 581M->362M(1024M), 0.0232360 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 607M->418M(1024M), 0.0239402 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001431 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0028039 secs]
[GC remark, 0.0014117 secs]
[GC cleanup 443M->435M(1024M), 0.0008195 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000128 secs]
[GC pause (G1 Evacuation Pause) (young) 757M->482M(1024M), 0.0289593 secs]
[GC pause (G1 Evacuation Pause) (mixed) 497M->400M(1024M), 0.0151496 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 443M->409M(1024M), 0.0029203 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001415 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0025849 secs]
[GC remark, 0.0029709 secs]
[GC cleanup 419M->414M(1024M), 0.0011728 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000366 secs]
[GC pause (G1 Evacuation Pause) (young)-- 832M->549M(1024M), 0.0386380 secs]
[GC pause (G1 Evacuation Pause) (mixed) 555M->489M(1024M), 0.0094833 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 490M->489M(1024M), 0.0026048 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001092 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0027967 secs]
[GC remark, 0.0032977 secs]
[GC cleanup 497M->495M(1024M), 0.0019432 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000190 secs]
[GC pause (G1 Evacuation Pause) (young)-- 867M->675M(1024M), 0.0090462 secs]
[GC pause (G1 Evacuation Pause) (mixed) 690M->587M(1024M), 0.0084562 secs]
[GC pause (G1 Evacuation Pause) (mixed) 649M->569M(1024M), 0.0073107 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 570M->569M(1024M), 0.0040867 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0002868 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0034601 secs]
[GC remark, 0.0029392 secs]
[GC cleanup 578M->568M(1024M), 0.0015519 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000656 secs]
执行结束!共生成对象次数:10117
```