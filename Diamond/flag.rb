def rot13(s)
    k = "N-ZA-Mn-za-m"
    return s.tr("A-Za-z", k)
  end
  
  def c(p)
    e = "F0_gu1f_1f_e0g13_J3yp0zr"
    d = rot13(e)
    if p == d
      puts "Correct password, here's your flag! : EG{#{d}}"
    else
      puts "Wrong password"
    end
  end
  
  print "Enter password: "
  password = gets.chomp
  
  c(password)