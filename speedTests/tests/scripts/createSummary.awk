BEGIN {
  OFS = "\t"
  critical_value = 2.62589
  meanCalc = 0
}

NR == FNR {
  sum1 += $1
  sum2 += $2
  sum3 += $3
  n++
}

NR != FNR && meanCalc == 0 {
  mean1 = sum1 / n
  mean2 = sum2 / n
  mean3 = sum3 / n
}

NR != FNR {
    stddev1 += ($1 - mean1)^2
    stddev2 += ($2 - mean2)^2
    stddev3 += ($3 - mean3)^2
}

END {
  stddev1 = sqrt(stddev1 / (n - 1))
  stddev2 = sqrt(stddev2 / (n - 1))
  stddev3 = sqrt(stddev3 / (n - 1))

  ci1 = critical_value * stddev1 / sqrt(n)
  ci2 = critical_value * stddev2 / sqrt(n)
  ci3 = critical_value * stddev3 / sqrt(n)

  printf "FILENAME: %s\n", FILENAME
  printf "Type\tMean(s)\t 99%% (+/-)\tConfidence Interval\n"
  printf "Real:\t%.3f\t  %.3f\t\t%.3f - %.3f\n", mean1, ci1, mean1-ci1, mean1+ci1
  printf "User:\t%.3f\t  %.3f\t\t%.3f - %.3f\n", mean2, ci2, mean2-ci2, mean2+ci2
  printf "Sys:\t%.3f\t  %.3f\t\t%.3f - %.3f\n", mean3, ci3, mean3-ci3, mean3+ci3
  printf "\n"
  printf "Samples: %d\n", n
  printf "Critical_value: %.3f\n\n", critical_value
}

