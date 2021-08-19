package Support;

import com.udojava.evalex.Expression;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Range {

  public static BigDecimal evaluatePrice(String itemList) {
    Pattern pattern = Pattern.compile("\\(.*?\\)");
    String list = itemList;
    Matcher matcher = pattern.matcher(list);
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      String item = list.substring(start, end);
      int itemPrice = getPrice(item);
      list = list.replace(item, Integer.toString(itemPrice));
      matcher = pattern.matcher(list);
    }
    BigDecimal expectedPrice = new Expression(list).eval();
    return expectedPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
  }

  public static int getPrice(String item) {
    switch (item) {
      /* LAMINATE WORKTOPS */
      case "(80348134 EKBACKEN 10-45*2.8)": //askmönstrad laminat
      case "(10345450 EKBACKEN 10-45*2.8)": //betongmönstrad laminat
      case "(70345447 EKBACKEN 10-45*2.8)": //brunsvart laminat
      case "(50345448 EKBACKEN 10-45*2.8)": //turkos laminat
      case "(30345454 EKBACKEN 10-45*2.8)": //vit högglans laminat
      case "(60345443 EKBACKEN 10-45*2.8)": //vit marmormönstrad laminat
      case "(50345453 EKBACKEN 10-45*2.8)": //ljus ekmönstrad laminat
      case "(10345445 EKBACKEN 10-45*2.8)": //off-white laminat
      case "(30345449 EKBACKEN 10-45*2.8)": //ljusgrå laminat
      case "(90345451 EKBACKEN 10-45*2.8)": //ljusgrå stenmönstrad laminat
      case "(70345452 EKBACKEN 10-45*2.8)": //mörk ekmönstrad laminat
      case "(90345446 EKBACKEN 10-45*2.8)": //mörkgrå laminat
      case "(30354311 EKBACKEN 10-45*2.8)": //mörkgrå laminat
      case "(80345442 EKBACKEN 10-45*2.8)": //svart laminat
      case "(40345444 EKBACKEN 10-45*2.8)": //svart stenmönstrad laminat
      case "(40340559 EKBACKEN 10-45*2.8)": //vit laminat
        return 599;
      case "(50348135 EKBACKEN 45.1-63.5*2.8)": //askmönstrad laminat
      case "(20345459 EKBACKEN 45.1-63.5*2.8)": //betongmönstrad laminat
      case "(60345462 EKBACKEN 45.1-63.5*2.8)": //brunsvart laminat
      case "(00345455 EKBACKEN 45.1-63.5*2.8)": //turkos laminat
      case "(80345456 EKBACKEN 45.1-63.5*2.8)": //ljus ekmönstrad laminat
      case "(60345457 EKBACKEN 45.1-63.5*2.8)": //mörk ekmönstrad laminat
      case "(90354313 EKBACKEN 45.1-63.5*2.8)": //mörkgrå laminat
      case "(90345465 EKBACKEN 45.1-63.5*2.8)": //svart stenmönstrad laminat
      case "(70345466 EKBACKEN 45.1-63.5*2.8)": //vit marmormönstrad laminat
      case "(00345460 EKBACKEN 45.1-63.5*2.8)": //ljusgrå laminat
      case "(40345458 EKBACKEN 45.1-63.5*2.8)": //ljusgrå stenmönstrad laminat
      case "(50345467 EKBACKEN 45.1-63.5*2.8)": //svart laminat
      case "(20345464 EKBACKEN 45.1-63.5*2.8)": //off-white laminat
      case "(40345463 EKBACKEN 45.1-63.5*2.8)": //mörkgrå laminat
      case "(30345430 EKBACKEN 45.1-63.5*2.8)": //vit laminat
      case "(80345461 EKBACKEN 45.1-63.5*2.8)": //vit högglans laminat
        return 699;
      case "(30348136 EKBACKEN 63.6-125*2.8)": //askmönstrad laminat
      case "(50345472 EKBACKEN 63.6-125*2.8)": //betongmönstrad laminat
      case "(80345475 EKBACKEN 63.6-125*2.8)": //brunsvart laminat
      case "(10345474 EKBACKEN 63.6-125*2.8)": //vit högglans laminat
      case "(00345479 EKBACKEN 63.6-125*2.8)": //vit marmormönstrad laminat
      case "(10345469 EKBACKEN 63.6-125*2.8)": //ljus ekmönstrad laminat
      case "(40345477 EKBACKEN 63.6-125*2.8)": //off-white laminat
      case "(30345473 EKBACKEN 63.6-125*2.8)": //ljusgrå laminat
      case "(70345471 EKBACKEN 63.6-125*2.8)": //ljusgrå stenmönstrad laminat
      case "(90345470 EKBACKEN 63.6-125*2.8)": //mörk ekmönstrad laminat
      case "(10354312 EKBACKEN 63.6-125*2.8)": //mörkgrå laminat
      case "(80345480 EKBACKEN 63.6-125*2.8)": //svart laminat
      case "(50345429 EKBACKEN 63.6-125*2.8)": //vit laminat
      case "(30345468 EKBACKEN 63.6-125*2.8)": //turkos laminat
      case "(60345476 EKBACKEN 63.6-125*2.8)": //mörkgrå laminat
      case "(20345478 EKBACKEN 63.6-125*2.8)": //svart stenmönstrad laminat
        return 1195;

      case "(00345484 SÄLJAN 10-45*3.8)": //aluminiummönstrad laminat
      case "(20345483 SÄLJAN 10-45*3.8)": //ekmönstrad laminat
      case "(70345485 SÄLJAN 10-45*3.8)": //vit högglans laminat
      case "(40345482 SÄLJAN 10-45*3.8)": //ljus valnötsmönstrad laminat
      case "(10345488 SÄLJAN 10-45*3.8)": //svart marmormönstrad laminat
      case "(30345487 SÄLJAN 10-45*3.8)": //svart mineralmönster laminat
      case "(50345486 SÄLJAN 10-45*3.8)": //valnötsmönstrad laminat
      case "(60345481 SÄLJAN 10-45*3.8)": //vit stenmönstrad laminat
      case "(90345489 SÄLJAN 10-45*3.8)": //svart laminat
      case "(20340560 SÄLJAN 10-45*3.8)": //vit laminat
        return 599;

      case "(90345494 SÄLJAN 45.1-63.5*3.8)": //vit högglans laminat
      case "(10345493 SÄLJAN 45.1-63.5*3.8)": //aluminiummönstrad laminat
      case "(30345492 SÄLJAN 45.1-63.5*3.8)": //ekmönstrad laminat
      case "(50345491 SÄLJAN 45.1-63.5*3.8)": //ljus valnötsmönstrad laminat
      case "(20345497 SÄLJAN 45.1-63.5*3.8)": //svart marmormönstrad laminat
      case "(40345496 SÄLJAN 45.1-63.5*3.8)": //svart mineralmönster laminat
      case "(60345495 SÄLJAN 45.1-63.5*3.8)": //valnötsmönstrad laminat
      case "(70345490 SÄLJAN 45.1-63.5*3.8)": //vit stenmönstrad laminat
      case "(00345498 SÄLJAN 45.1-63.5*3.8)": //svart laminat
      case "(90345432 SÄLJAN 45.1-63.5*3.8)": //vit laminat
        return 699;

      case "(90345502 SÄLJAN 63.6-125*3.8)": //aluminiummönstrad laminat
      case "(10345501 SÄLJAN 63.6-125*3.8)": //ekmönstrad laminat
      case "(70345503 SÄLJAN 63.6-125*3.8)": //vit högglans laminat
      case "(30345500 SÄLJAN 63.6-125*3.8)": //ljus valnötsmönstrad laminat
      case "(00345506 SÄLJAN 63.6-125*3.8)": //svart marmormönstrad laminat
      case "(20345505 SÄLJAN 63.6-125*3.8)": //svart mineralmönster laminat
      case "(50345504 SÄLJAN 63.6-125*3.8)": //valnötsmönstrad laminat
      case "(80345499 SÄLJAN 63.6-125*3.8)": //vit stenmönstrad laminat
      case "(80345507 SÄLJAN 63.6-125*3.8)": //svart laminat
      case "(10345431 SÄLJAN 63.6-125*3.8)": //vit laminat
        return 1195;

      case "(90345559 DEJE 10-45*7.7)": //vit högglans laminat
      case "(70345560 DEJE 10-45*7.7)": //svart laminat
      case "(70345555 DEJE 10-45*7.7)": //vit laminat
        return 1795;
      case "(30345557 DEJE 45.1-63.5*7.7)": //vit högglans laminat
      case "(10345563 DEJE 45.1-63.5*7.7)": //svart laminat
      case "(10345558 DEJE 45.1-63.5*7.7)": //vit laminat
        return 1995;
      case "(50345561 DEJE 63.6-125*7.7)": //vit högglans laminat
      case "(30345562 DEJE 63.6-125*7.7)": //svart laminat
      case "(50345556 DEJE 63.6-125*7.7)": //vit laminat
        return 2995;

      /* WOOD WORKTOPS */
      case "(60347545 KARLBY 10-45*3.8)": //bok
      case "(40347546 KARLBY 10-45*3.8)": //björk
        return 995;
      case "(20347547 KARLBY 10-45*3.8)": //ek
        return 1295;
      case "(00347548 KARLBY 45.1-63.5*3.8)": //bok
      case "(80347549 KARLBY 45.1-63.5*3.8)": //björk
        return 1295;
      case "(60347550 KARLBY 45.1-63.5*3.8)": //ek
        return 1495;
      case "(50351316 KARLBY 45.1-63.5*3.8)": //valnöt
        return 1995;
      case "(90347558 BARAKABODA 45.1-63.5*3.8)": //valnöt
        return 2995;
      case "(50347555 MÖLLEKULLA 45.1-63.5*3.8)": //valnöt
        return 1795;
      case "(40347551 KARLBY 63.6-125*3.8)": //bok:
      case "(20347552 KARLBY 63.6-125*3.8)": //björk:
        return 1995;
      case "(00347553 KARLBY 63.6-125*3.8)": //ek:
        return 2295;
      case "(30351317 KARLBY 63.6-125*3.8)": //valnöt:
        return 2995;

      case "(10347557 BARKABODA 10-45*3.8)": //valnöt
        return 1995;
      case "(90347558 BARKABODA 45.1-63.5*3.8)": //valnöt
        return 2995;
      case "(70347559 BARKABODA 63.6-125*3.8)": //valnöt
        return 3995;

      /* SOLID WOOD WORKTOPS */
      case "(40340564 SKOGARP 10-45*4.0)": //bok
      case "(90345564 SKOGARP 10-45*4.0)": //björk
        return 1295;
      case "(60345.65 SKOGARP 10-45*4.0)": //oak
        return 1795;
      case "(80345437 SKOGARP 45.1-63.5*4.0)": //bok
      case "(40345566 SKOGARP 45.1-63.5*4.0)": //björk
        return 1495;
      case "(20345567 SKOGARP 45.1-63.5*4.0)": //oak
        return 1995;
      case "(60345438 SKOGARP 63.6-125*4.0)": //bok
      case "(00345568 SKOGARP 63.6-125*4.0)": //björk
        return 2495;
      case "(80345569 SKOGARP 63.6-125*4.0)": //oak
        return 2995;


      /* STONE WORKTOPS */
      case "(60345532 OXSTEN 10-45*3.8)": //vit kompositsten
      case "(70345517 OXSTEN 10-45*3.8)": //antracit stenmönstrad, kompositsten
      case "(60345508 OXSTEN 10-45*3.8)": //beige stenmönstrad, kompositsten
      case "(40345514 OXSTEN 10-45*3.8)": //ljusgrå stenmönstrad, kompositsten
      case "(10345515 OXSTEN 10-45*3.8)": //mörkgrå stenmönstrad, kompositsten
      case "(90345516 OXSTEN 10-45*3.8)": //svart stenmönstrad, kompositsten
      case "(20345510 OXSTEN 10-45*3.8)": //vit marmormönstrad, kompositsten
      case "(00345511 OXSTEN 10-45*3.8)": //vit mineralmönster, kompositsten
        return 1995;
      case "(80354318 OXSTEN 10-45*3.8)": //matt mörkbrun matt mörkgrå, marmormönstrad kompositsten
        return  3495;
      case "(20345435 OXSTEN 45.1-63.5*3.8)": //vit kompositsten
      case "(30345524 OXSTEN 45.1-63.5*3.8)": //antracit stenmönstrad, kompositsten
      case "(10345520 OXSTEN 45.1-63.5*3.8)": //beige stenmönstrad, kompositsten
      case "(50345518 OXSTEN 45.1-63.5*3.8)": //ljusgrå stenmönstrad, kompositsten
      case "(70345522 OXSTEN 45.1-63.5*3.8)": //mörkgrå stenmönstrad, kompositsten
      case "(50345523 OXSTEN 45.1-63.5*3.8)": //svart stenmönstrad, kompositsten
      case "(90345521 OXSTEN 45.1-63.5*3.8)": //vit marmormönstrad, kompositsten
      case "(30345519 OXSTEN 45.1-63.5*3.8)": //vit mineralmönster, kompositsten
        return 2995;
      case "(60354319 OXSTEN 45.1-63.5*3.8)": //matt mörkbrun matt mörkgrå, marmormönstrad kompositsten
        return 3995;
      case "(00345436 OXSTEN 63.6-125*3.8)": //vit kompositsten
      case "(80345531 OXSTEN 63.6-125*3.8)": //antracit stenmönstrad, kompositsten
      case "(60345527 OXSTEN 63.6-125*3.8)": //beige stenmönstrad, kompositsten
      case "(00345525 OXSTEN 63.6-125*3.8)": //ljusgrå stenmönstrad, kompositsten
      case "(20345529 OXSTEN 63.6-125*3.8)": //mörkgrå stenmönstrad, kompositsten
      case "(00345530 OXSTEN 63.6-125*3.8)": //svart stenmönstrad, kompositsten
      case "(40345528 OXSTEN 63.6-125*3.8)": //vit marmormönstrad, kompositsten
      case "(80345526 OXSTEN 63.6-125*3.8)": //vit mineralmönster, kompositsten
        return 3995;
      case "(40354320 OXSTEN 63.6-125*3.8)": //matt mörkbrun matt mörkgrå, marmormönstrad kompositsten
        return 5495;

      /* ACRYLIC WORKTOPS */
      case "(10345539 FORNBY 10-45*3.8)": //vit akrylplast
        return 1995;
      case "(50345434 FORNBY 45.1-63.5*3.8)": //vit akrylplast
        return 2495;
      case "(70345433 FORNBY 63.6-125*3.8)": //vit akrylplast
        return 2995;

      /* WALL PANELS LAMINATE */
      case "(70216662 SIBBARP 1 m2)": //aluminiummönstrad laminat
      case "(40311972 SIBBARP 1 m2)": //vit marmormönstrad/laminat
      case "(20216674 SIBBARP 1 m2)": //off-white laminat
      case "(40283078 SIBBARP 1 m2)": //vit stenmönstrad/laminat
      case "(10216679 SIBBARP 1 m2)": //ljus valnötsmönstrad laminat
      case "(20311973 SIBBARP 1 m2)": //mörk ekmönstrad/laminat
      case "(40216668 SIBBARP 1 m2)": //svart stenmönstrad/laminat
      case "(80216666 SIBBARP 1 m2)": //ekmönstrad laminat
      case "(30311977 SIBBARP 1 m2)": //ljusgrå stenmönstrad/laminat
      case "(30395533 SIBBARP 1 m2)": //askmönstrad laminat
      case "(00216665 SIBBARP 1 m2)": //högglans vit/laminat
      case "(10216684 SIBBARP 1 m2)": //valnötsmönstrad laminat
      case "(00311974 SIBBARP 1 m2)": //betongmönstrad/laminat
      case "(40238203 SIBBARP 1 m2)": //brunsvart laminat
      case "(60311947 SIBBARP 1 m2)": //svart marmormönstrad/laminat
      case "(70311975 SIBBARP 1 m2)": //ljus ekmönstrad laminat
      case "(90216675 SIBBARP 1 m2)": //mörkgrå/laminat
      case "(50395532 SIBBARP 1 m2)": //mörkgrå linnemönstrad/laminat
      case "(60238141 SIBBARP 1 m2)": //ljusgrå laminat
      case "(60216686 SIBBARP 1 m2)": //vit laminat
      case "(80216671 SIBBARP 1 m2)": //svart mineralmönster/laminat
      case "(50216663 SIBBARP 1 m2)": //svart laminat
        return 995;
      case "(80193721 SIBBARP 1 m2)": //rostfritt stålfärg/laminat
        return 1495;

      /* WALL PANELS GLASS */
      case "(60216752 ISHULT 1 m2)": //svart glas
      case "(80216751 ISHULT 1 m2)": //vit glas
        return 2495;

      //* WALL PANELS QUARTZ */
      case "(10216622 RÅHULT 1 m2)": //ljusgrå stenmönstrad/kompositsten
      case "(20216626 RÅHULT 1 m2)": //antracit stenmönstrad/kompositsten
      case "(00395638 RÅHULT 1 m2)": //matt mörkgrå/marmormönstrad kompositsten
      case "(90311979 RÅHULT 1 m2)": //vit marmormönstrad kompositsten
      case "(90216623 RÅHULT 1 m2)": //svart stenmönstrad/kompositsten
      case "(70216624 RÅHULT 1 m2)": //vit mineralmönster/kompositsten
      case "(40216625 RÅHULT 1 m2)": //mörkgrå stenmönstrad/kompositsten
      case "(50311995 RÅHULT 1 m2)": //vit kompositsten
        return 2995;

      //* WALL PANELS ACRYLIC */
      case "(40216649 KLINGSTA 1 m2)": //vit akrylplast
        return 1995;
        //1000 + 635 + 635 = 2270 mm minus 2*bredden på wall panels (2256)
        //2,270 * 0,550 = 1,2485 m skilt från 1,231 som blir värdet i item list

        //Längden på alla väggar * höjden = height för splashback

      //* WALL EDGING STRIP */
      case "(00345950 OTTARP)": //quartz
        return 599;


      /* SINKS STAINLESS STEEL */
      case "(10333640 NORRSJÖN inset sink 1 bowl stainless steel 21x44 cm)": //Underglued laminate
        return 1495;
      case "(49157650 NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm)": //Top Mounted
        return 1995;
      case "(00353346 NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm)": //Underglued laminate
        return 4195;
      case "(80353347 NORRSJÖN inset sink 1 bowl stainless steel 37x44 cm)": //Underglued quartz
        return 4695;
      case "(49157909 NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm)": //Top Mounted
        return 2995;
      case "(80353352 NORRSJÖN inset sink 1 bowl stainless steel 54x44 cm)": //Underglued laminate
        return 4495;
      case "(39157919 NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm)": //Top Mounted
        return 3995;
      case "(70338668 NORRSJÖN inset sink 2 bowls stainless steel 73x44 cm)": //Underglued laminate
        return 5495;
      case "(19157915 NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm)": //Top Mounted
        return 3495;
      case "(60353353 NORRSJÖN inset sink 1 bowl stainless steel 73x44 cm)": //Underglued laminate
        return 4995;
      case "(59157391 LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm)": //Top Mounted
        return 699;
      case "(20338661 LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm)": //Underglued laminate
        return 2795;
      case "(10352011 LÅNGUDDEN inset sink 1 bowl stainless steel 46x46 cm)": //Underglued acrylic
        return 2795;
      case "(29157477 LÅNGUDDEN inset sink 1 bowl stainless steel 56x53 cm)": //Top Mounted
        return 899;
      case "(00338662 LÅNGUDDEN inset sink 1 bowl stainless steel 56x53 cm)": //Underglued laminate
        return 2995;
      case "(19157487 LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm)": //Top Mounted
        return 1295;
      case "(80338663 LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm)": //Underglued laminate
        return 3495;
      case "(70352032 LÅNGUDDEN inset sink 2 bowls stainless steel 75x53 cm)": //Underglued acrylic
        return 3495;
      case "(49140631 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm)": //Top Mounted
        return 995;
      case "(80338658 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm)": //Underglued laminate
        return 2995;
      case "(20351935 HILLESJÖN inset sink 1 1/2 bowl stainless steel 58x46 cm)": //Underglued quartz
        return 3495;
      case "(29171138 HILLESJÖN inset sink 1 1/2 bowl stainless steel 75x46 cm)": //Top Mounted
        return 1295;
      case "(60338659 HILLESJÖN inset sink 1 1/2 bowl stainless steel 75x46 cm)": //Underglued laminate
        return 3195;
      case "(69158168 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm)": //Top Mounted
        return 799;
      case "(30352270 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 69x47 cm)": //Underglued laminate
        return 2795;
      case "(29158189 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 86x47 cm)": //Top Mounted
        return 995;
      case "(30338670 VATTUDALEN inset sink 1 bowl with drainboard stainless steel 86x47 cm)": //Underglued laminate
        return 2995;
      case "(90338672 VATTUDALEN inset sink 1 1/2 bowl w drainboard stainless steel 87x53 cm)": //Top Mounted
        return 3195;
      case "(89158191 VATTUDALEN 88x53)": //Underglued laminate
        return 1295;
      case "(09158190 VATTUDALEN 109x53)": //Top Mounted
        return 1495;
      case "(99157501 BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm)": //Top Mounted
        return 599;
      case "(60348031 BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm)": //Underglued laminate
        return 2495;
      case "(90351932 BOHOLMEN inset sink 1 bowl stainless steel 47x30 cm)": //Underglued acrylic
        return 2495;
      case "(79157494 BOHOLMEN 45)": //Top Mounted
        return 599;
      case "(59158164 AMMERÅN onset sink 1 bowl stainless steel 60x635 cm)": //Fit Between
        return 1395;

      /* SINKS COMPOSITE */
      case "(49157645 HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm)": //Top mounted
      case "(79157639 HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm)": //Top mounted
        return 1995;

      case "(40348013 HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm)": //Underglued laminate
      case "(00351984 HÄLLVIKEN inset sink 1 bowl white quartz composite 56x50 cm)": //Underglued wood
      case "(60348012 HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm)": //Underglued laminate
      case "(70351966 HÄLLVIKEN inset sink 1 bowl black quartz composite 56x50 cm)": //Underglued wood
        return 3495;
      case "(59158197 HÄLLVIKEN 100x50 white)": //Top mounted, quartz composite
      case "(79158196 HÄLLVIKEN 100x50 black)": //Top mounted, quartz composite
        return 2795;

      /* SINKS PORCELAIN */
      case"(80377843 havseninsetsink1bowlwhite53x47cm)": //Top mounted
        return 995;
      case"(39158179 DOMSJÖ onset sink 1 bowl white 62x66 cm)": //Fit Between
        return 1795;
      case"(49158174 DOMSJÖ onset sink 2 bowls white 83x66 cm)": //Fit Between
        return 1995;
      case"(20359229 havsensinkbowlwvisiblefrontwhite62x48cm)": //Fit Between
        return 1495;
      case"(90359216 havsensinkbowl2bowlswvisiblefrontwhite82x48cm)": //Fit Between
        return 1995;


      /* EDGES LAMINATE */
      case "(20350182 Same Color Edge Laminate)":
        return 99;
      case "(60340558 Aluminium Edge 28 Laminate)":
        return 299;
      case "(60345570 Aluminium Edge 38 Laminate)":
        return 299;
      case "(40345571 Aluminium Edge 77 Laminate)":
        return 499;

      /* EDGES WOOD */
      case "(80350184 Same Color Edge Wood)":
        return 299;

        /* EDGES SOLID WOOD */
      case "(00350183 Same Color Edge Solid Wood)":
        return 299;

      /* EDGES QUARTZ */
      case "(30350313 Same Color Edge Quartz)":
        return 699;

      /* EDGES ACRYLIC */
      case "(50350312 Same Color Edge Acrylic)":
        return 499;

      /* PREPARATION LAMINATE */
      case "(10345577 Joint Laminate)":
        return 399;
      case "(00345573 Tap Hole Laminate)":
        return 300;
      case "(10345577 Drainer Grooves Laminate)":
        return 399;
      case "(10345577 Rounded Corner Laminate)":
        return 399;
      case "(10345577 Inverted Corner Laminate)":
        return 399;
      case "(10345577 Single Cut Laminate)":
        return 399;
      case "(10345577 Double Cut Laminate)":
        return 399;
      case "(10345577 Triple Cut Laminate)":
        return 399;
      case "(10345577 Cut Out Laminate)":
        return 399;
      case "(10345577 Preparation Laminate)":
        return 399;
      case "(10346261 WP Cut out Laminate)":
        return 300;
      case "(80345946 WP Preparation Laminate)":
        return 600;

      /* PREPARATION WOOD */
      case "(10347562 Joint Wood)":
        return 599;
      case "(30347561 Tap Hole Wood)":
        return 300;
      case "(10347562 Drainer Grooves Wood)":
        return 599;
      case "(10347562 Rounded Corner Wood)":
        return 599;
      case "(10347562 Inverted Corner Wood)":
        return 599;
      case "(10347562 Single Cut Wood)":
        return 599;
      case "(10347562 Double Cut Wood)":
        return 599;
      case "(10347562 Triple Cut Wood)":
        return 599;
      case "(10347562 Cut Out Wood)":
        return 599;
      case "(10347562 Preparation Wood)":
        return 599;

      /* PREPARATION SOLID WOOD */
      case "(70345579 Joint Solid Wood)":
        return 599;
      case "(30345576 Tap Hole Solid Wood)":
        return 300;
      case "(70345579 Drainer Grooves Solid Wood)":
        return 599;
      case "(70345579 Rounded Corner Solid Wood)":
        return 599;
      case "(70345579 Inverted Corner Solid Wood)":
        return 599;
      case "(70345579 Single Cut Solid Wood)":
        return 599;
      case "(70345579 Double Cut Solid Wood)":
        return 599;
      case "(70345579 Triple Cut Solid Wood)":
        return 599;
      case "(70345579 Cut Out Solid Wood)":
        return 599;
      case "(70345579 Preparation Solid Wood)":
        return 599;

      /* PREPARATION QUARTZ */
      case "(50345580 Joint Quartz)":
        return 799;
      case "(80345574 Tap Hole Quartz)":
        return 300;
      case "(80345588 Drainer Grooves Quartz)":
        return 2295;
      case "(50345580 Rounded Corner Quartz)":
        return 799;
      case "(50345580 Inverted Corner Quartz)":
        return 799;
      case "(50345580 Single Cut Quartz)":
        return 799;
      case "(50345580 Double Cut Quartz)":
        return 799;
      case "(50345580 Triple Cut Quartz)":
        return 799;
      case "(50345580 Cut Out Quartz)":
        return 799;
      case "(30346260 WP Cut out Quartz)":
        return 300;
      case "(60345947 WP Preparation Quartz)":
        return 600;


      /* PREPARATION ACRYLIC */
      case "(90345578 Joint Acrylic)":
        return 599;
      case "(50345575 Tap Hole Acrylic)":
        return 300;
      case "(40345585 Drainer Grooves Acrylic)":
        return 1995;
      case "(90345578 Rounded Corner Acrylic)":
        return 599;
      case "(90345578 Inverted Corner Acrylic)":
        return 599;
      case "(90345578 Single Cut Acrylic)":
        return 599;
      case "(90345578 Double Cut Acrylic)":
        return 599;
      case "(90345578 Triple Cut Acrylic)":
        return 599;
      case "(90345578 Cut Out Acrylic)":
        return 599;
      case "(90345578 Preparation Acrylic)":
        return 599;
      case "(50346264 WP Cut out Acrylic)":
        return 300;
      case "(30345944 WP Cut out Acrylic)":
        return 600;

     /* PREPARATION GLASS */
      case "(40345948 WP Cut out Glass)":
        return 300;
      case "(00345945 WP Preparation Glass)":
        return 600;

      /* OVERHANG EDGE */
      case "(00346271 Laminate edge 45.1-63.5)":
        return 349;
      case "(00351173 Wood edge 10-45)":
        return 499;
      case "(80351174 Wood edge 45.1-63.5)":
        return 799;
      case "(10351177 Wood edge 63.6-125)":
        return 995;

      default:
        Assert.fail("Can't find price for item " + item);
    }
    return 0;
  }
}
