from matplotlib import pyplot as plt
from numpy import arange


max = 40
size = list(range(1, max + 1))

bellon_t1 = [0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
             0.5660941266116702, 0.5628153328347215, 0.5638083537539417, 0.566130467884174, 0.5669015218609474,
             0.5685323910912894, 0.5731881509037084, 0.570113120153401, 0.5738031570537699, 0.5761094301165004,
             0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
             0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
             0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
             0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
             0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664]

# SLOW RUN
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5617102366189244,
# 0.5598210638810431, 0.5625929844573917, 0.5457886735571401, 0.566130467884174, 0.5693615464611934,
# 0.5481756875242539, 0.5731881509037084, 0.570113120153401, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664

# SEPARATED INDEX/SEARCH (1)
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
# 0.5620928573034786, 0.5632079906074531, 0.565960875279157, 0.5672933886042902, 0.5693895012861963,
# 0.5704821238434379, 0.5707281263034625, 0.5731881509037084, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5179246823099818,
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
# 0.5620928573034786, 0.5632079906074531, 0.565960875279157, 0.5672933886042902, 0.5693895012861963,
# 0.5704821238434379, 0.5707281263034625, 0.5731881509037084, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5179246823099818
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
# 0.5620928573034786, 0.5632079906074531, 0.565960875279157, 0.5672933886042902, 0.5693895012861963,
# 0.5704821238434379, 0.5707281263034625, 0.5731881509037084, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5179246823099818,

# SEPARATED INDEX/SEARCH (2)
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
# 0.5651388973422548, 0.5625929844573917, 0.562270838378788, 0.5672933886042902, 0.5693615464611934,
# 0.5695234632029674, 0.5702361213834132, 0.5731881509037084, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5260502181107943, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
# 0.5651388973422548, 0.5625929844573917, 0.562270838378788, 0.5672933886042902, 0.5693615464611934,
# 0.5695234632029674, 0.5702361213834132, 0.5731881509037084, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5260502181107943, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
# 0.5651388973422548, 0.5625929844573917, 0.562270838378788, 0.5672933886042902, 0.5693615464611934,
# 0.5695234632029674, 0.5702361213834132, 0.5731881509037084, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
# 0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
# 0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
# 0.5375508331169445, 0.5348448060566738, 0.5260502181107943, 0.5249142655747985, 0.526759284024983,
# 0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664

# SEQUENTIAL INDEX
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5614365455703255, 0.5588601686445605,
# 0.5623463983251966, 0.5656092179164295, 0.5570394423148275, 0.5629883455538598, 0.5616609491849505,
# 0.5501958562969944, 0.5680316837546214, 0.5759556785789851, 0.5738031570537699, 0.5733419024412237,
# 0.5708818778409779, 0.5671918409406089, 0.5641196056728017, 0.5622643371201161, 0.5638018524952699,
#
# Type 1 max MRR is at 13 with MRR of 0.5759556785789851
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5586485176900466, 0.562355993076489,
# 0.5670238242203347, 0.5752225750390116, 0.5662683783541876, 0.5616880468365869, 0.5619814726604555,
# 0.5615467377862805, 0.5686819592252503, 0.5700362443846432, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
#
# Type 1 max MRR is at 15 with MRR of 0.5761094301165004
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5597422169387275, 0.5630940004565627,
# 0.5577977453285298, 0.5699379150495545, 0.5613940390702544, 0.563910854778952, 0.5600106574977584,
# 0.5630669068341251, 0.5728287317251012, 0.5665316137501017, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5614545790225353, 0.5622643371201161, 0.5638018524952699,
#
# Type 1 max MRR is at 15 with MRR of 0.5761094301165004
#
# 0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5597422169387275, 0.5630940004565627,
# 0.5577977453285298, 0.5699379150495545, 0.5613940390702544, 0.563910854778952, 0.5600106574977584,
# 0.5630669068341251, 0.5728287317251012, 0.5665316137501017, 0.5738031570537699, 0.5761094301165004,
# 0.5708818778409779, 0.5671918409406089, 0.5614545790225353, 0.5622643371201161, 0.5638018524952699,
#
# Type 1 max MRR is at 15 with MRR of 0.5761094301165004

# AFTER CHECKING THE RANKING ISSUE
bellon_t1_1 = [
        0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5595505267101368, 0.5617102366189244,
        0.5509854440866823, 0.5590836056136033, 0.5454811704821093, 0.5676623922943271, 0.5693615464611934,
        0.5682329584946416, 0.5731881509037084, 0.570113120153401, 0.5738031570537699, 0.5761094301165004,
        0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
        0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
        0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
        0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
        0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5193434971974313
    ]
#
# Type 1 max MRR is at 15 with MRR of 0.5761094301165004
#
bellon_t1_2 = [
        0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5617102366189244,
        0.5552495181655861, 0.5576114346418937, 0.5655830857869764, 0.5676623922943271, 0.5693615464611934,
        0.5713079892449491, 0.5666722707300037, 0.5701131201534009, 0.5738031570537699, 0.5736494055162544,
        0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
        0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
        0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
        0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
        0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5207742108052669
    ]
#
# Type 1 max MRR is at 14 with MRR of 0.5738031570537699
#
bellon_t1_3 = [
        0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5617102366189244,
        0.5507355366656327, 0.5605634641621887, 0.5479411950823553, 0.5672933886042902, 0.5693615464611934,
        0.5695234632029674, 0.5731881509037084, 0.570113120153401, 0.5738031570537699, 0.5761094301165004,
        0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
        0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
        0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
        0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
        0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664
    ]
#
# Type 1 max MRR is at 15 with MRR of 0.5761094301165004
#
bellon_t1_4 = [
        0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5617102366189244,
        0.555208517755582, 0.5637263529339336, 0.5651054576340714, 0.5676623922943271, 0.5693615464611934,
        0.5503292172562725, 0.571343132453524, 0.5701131201534009, 0.5738031570537699, 0.5736494055162544,
        0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
        0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5598850743164991, 0.5564820402861589,
        0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
        0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
        0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5207742108052669
    ]
#
# Type 1 max MRR is at 14 with MRR of 0.5738031570537699
#
bellon_t1_5 = [
        0.48506527797917526, 0.539530414354991, 0.562222514659963, 0.5609855410602803, 0.5630940004565627,
        0.5620350545827871, 0.5687079027494316, 0.5638083537539417, 0.5637637880908938, 0.5693895012861963,
        0.5682329584946416, 0.571343132453524, 0.5682681017032165, 0.5738031570537699, 0.5736494055162544,
        0.5708818778409779, 0.5671918409406089, 0.5647346118228632, 0.5622643371201161, 0.5638018524952699,
        0.5658006224829698, 0.5648051235169912, 0.5611150866166222, 0.5580400558663147, 0.5564820402861589,
        0.5583270587363434, 0.5479905625142385, 0.5460071676802901, 0.543547143080044, 0.543547143080044,
        0.5375508331169445, 0.5348448060566738, 0.5291252488611018, 0.5249142655747985, 0.526759284024983,
        0.5221467378995217, 0.5226676842854561, 0.5172248798574118, 0.5187213948225614, 0.5197697007601664
    ]
#
# Type 1 max MRR is at 14 with MRR of 0.5738031570537699
#

bellon_t2_1 = [
        0.20926548788012778, 0.2783360389627968, 0.3111033385169024, 0.32130126251700986, 0.3299005733759096,
        0.3292520637498434, 0.3285375401275418, 0.33184918022255677, 0.31768634570431026, 0.31460853547791,
        0.31943245558207345, 0.31559611691939626, 0.31656518245748966, 0.32103208936473127, 0.32061082073762237,
        0.3190638024802062, 0.3336005727531852, 0.33206511560343704, 0.3328027935958994, 0.33319228704193626,
        0.332413669536882, 0.3325169296655939, 0.314682883947979, 0.3330977381398633, 0.332505826419809,
        0.3292314581610484, 0.3274625365879889, 0.32571025289060757, 0.32308319574659466, 0.320611275520204,
        0.3207482972898641, 0.32112066337530515, 0.32086682105050046, 0.3201988442402352, 0.3212364559940088,
        0.3184693036585782, 0.31057660055621594, 0.3067445707959212, 0.28649715632610034, 0.2740651372680245
    ]
#
# Type 2 max MRR is at 17 with MRR of 0.3336005727531852
#
bellon_t2_2 = [
        0.20926548788012778, 0.2783360389627968, 0.3111033385169024, 0.32130126251700986, 0.3299005733759096,
        0.32924941694243365, 0.3285375401275418, 0.33184918022255677, 0.31768634570431026, 0.314620512196714,
        0.3189862262201814, 0.3156439272081704, 0.3161189530955977, 0.3215898760670963, 0.32101532680522193,
        0.3201347529487471, 0.33357301152789187, 0.3320540148319341, 0.33286037116912437, 0.3327460576800442,
        0.3324568966132416, 0.3331996926781877, 0.3150938846760374, 0.33410175420412036, 0.332505826419809,
        0.32833996334865834, 0.3274625365879889, 0.32622071285555215, 0.32308319574659466, 0.320611275520204,
        0.32030886852939255, 0.3211198665371589, 0.32086682105050046, 0.3201988442402352, 0.3212364559940088,
        0.3184693036585782, 0.31057660055621594, 0.3067445707959212, 0.28649715632610034, 0.2743999502301123
    ]
#
# Type 2 max MRR is at 24 with MRR of 0.33410175420412036
#
bellon_t2_3 = [
        0.20926548788012778, 0.2783360389627968, 0.3111033385169024, 0.32130126251700986, 0.3299005733759096,
        0.329247103541364, 0.3285375401275418, 0.33184918022255677, 0.31768634570431026, 0.31460398325998623,
        0.31943245558207345, 0.31515184470382834, 0.3161353996630139, 0.3215898760670963, 0.32065544367381155,
        0.3192311384909157, 0.3334639150111057, 0.33260059083770743, 0.3323565642340074, 0.3327460576800442,
        0.3315797770237726, 0.3331913444966352, 0.31524273596006763, 0.33415753287435684, 0.3323384904090996,
        0.3286736714586833, 0.3274625365879889, 0.32622071285555215, 0.32308319574659466, 0.320611275520204,
        0.3207482972898641, 0.32110320537591946, 0.32086682105050046, 0.3201988442402352, 0.32070098075973835,
        0.3184693036585782, 0.31057660055621594, 0.3067445707959212, 0.28649715632610034, 0.2740638076212202
    ]
#
# Type 2 max MRR is at 24 with MRR of 0.33415753287435684
#

bellon_t2_4 = [
        0.20926548788012778, 0.2783360389627968, 0.3111033385169024, 0.32130126251700986, 0.3299005733759096,
        0.3292520637498434, 0.3285375401275418, 0.33184904554568695, 0.31768634570431026, 0.31462485800475654,
        0.3189862262201814, 0.31526144489797725, 0.3165816290249059, 0.32103208936473127, 0.3207893124823792,
        0.31943194170376715, 0.33379612620883786, 0.33260436437826757, 0.3307995666428733, 0.33319228704193626,
        0.3324591651237536, 0.33331125001866074, 0.31539073093018233, 0.3341372663864408, 0.33289760517504163,
        0.32907177414111316, 0.32667526049950796, 0.32622071285555215, 0.32263696638470263, 0.320611275520204,
        0.3207482972898641, 0.32110320537591946, 0.32086682105050046, 0.3201988442402352, 0.3212364559940088,
        0.3184693036585782, 0.31057660055621594, 0.3067445707959212, 0.28649715632610034, 0.27410063341473123
    ]
#
# Type 2 max MRR is at 24 with MRR of 0.3341372663864408
#
bellon_t2_5 = [
    0.20926548788012778, 0.2783360389627968, 0.3111033385169024, 0.32130126251700986, 0.3299005733759096,
    0.3292520637498434, 0.3285375401275418, 0.33184918022255677, 0.31768634570431026, 0.3146043915833309,
    0.3189862262201814, 0.3156439272081704, 0.3160838468317048, 0.32181907569388635, 0.32006339388000116,
    0.31939847450162523, 0.3336256731547916, 0.3325931536816759, 0.33286037116912437, 0.3327460576800442,
    0.33123159710436667, 0.3329954120000716, 0.3150938846760374, 0.33410175420412036, 0.33289760517504163,
    0.32833996334865834, 0.3274625365879889, 0.32571025289060757, 0.32263696638470263, 0.320611275520204,
    0.3207491216051878, 0.32110320537591946, 0.32086682105050046, 0.3201988442402352, 0.3212364559940088,
    0.3184693036585782, 0.3105981953097469, 0.3067445707959212, 0.28649715632610034, 0.2743999502301123
]
#
# Type 2 max MRR is at 24 with MRR of 0.33410175420412036
#
bellon_t3_1 = [
        0.024086272288553298, 0.14935615907137875, 0.1636409987699643, 0.17793268436448548, 0.19060799466217285,
        0.19273901367338866, 0.19452370741369837, 0.1998104097475886, 0.20227447621597477, 0.198855169159512,
        0.20369323744482448, 0.20506770406500857, 0.2068035724061262, 0.20687853303960477, 0.19953563242606231,
        0.1980600680590136, 0.195632031195025, 0.19791123712225886, 0.19771076787304281, 0.19565218085103583,
        0.19663163223519062, 0.19597158798122577, 0.19613769330478942, 0.19250580430825256, 0.19017366880632297,
        0.1896857471807844, 0.1900430725279835, 0.19170979457969717, 0.18999052787556792, 0.19151775180341657,
        0.18913022393479725, 0.18982512485719882, 0.18548769872474322, 0.18052398353403104, 0.17700067413680656,
        0.17194265031769396, 0.1721783335962553, 0.16815874601416286, 0.16543812310618006, 0.1635686738022792
    ]
#
# Type 3 max MRR is at 14 with MRR of 0.20687853303960477
#
bellon_t3_2 = [
        0.024086272288553298, 0.14935615907137875, 0.1636409987699643, 0.17793268436448548, 0.19060799466217285,
        0.19273901367338866, 0.19452370741369837, 0.1998104097475886, 0.20223929962951073, 0.198855169159512,
        0.20369323744482448, 0.20506770406500857, 0.2068035724061262, 0.20687853303960477, 0.19953563242606231,
        0.1980600680590136, 0.19757918890545473, 0.1956574229752408, 0.19892436010605252, 0.1960383238342662,
        0.19663163223519062, 0.1942321057805785, 0.19613769330478942, 0.19250580430825256, 0.1885555458289767,
        0.1896857471807844, 0.18989910930386056, 0.19170979457969717, 0.18999052787556792, 0.1914805040560624,
        0.18913022393479725, 0.18982512485719882, 0.18548769872474322, 0.18052398353403104, 0.17700067413680656,
        0.17194265031769396, 0.1721783335962553, 0.16815874601416286, 0.16543812310618006, 0.1635686738022792,
    ]
#
# Type 3 max MRR is at 14 with MRR of 0.20687853303960477
#
bellon_t3_3 = [
        0.024086272288553298, 0.14935615907137875, 0.1636409987699643, 0.17793268436448548, 0.19060799466217285,
        0.19273901367338866, 0.19452370741369837, 0.1998104097475886, 0.20223929962951073, 0.198855169159512,
        0.20369323744482448, 0.20506770406500857, 0.2068035724061262, 0.20687853303960477, 0.19953563242606231,
        0.19770140052672489, 0.19718043717175154, 0.19559437922287667, 0.19892436010605252, 0.19638033619084166,
        0.19663163223519062, 0.1942321057805785, 0.19613769330478942, 0.19250580430825256, 0.1885555458289767,
        0.1898677860157358, 0.18989910930386056, 0.19170979457969717, 0.18999052787556792, 0.19151775180341657,
        0.18911805759662176, 0.18982512485719882, 0.18548769872474322, 0.18052398353403104, 0.17700067413680656,
        0.17194265031769396, 0.1721783335962553, 0.16815874601416286, 0.16543812310618006, 0.1635686738022792
    ]
#
# Type 3 max MRR is at 14 with MRR of 0.20687853303960477
#
bellon_t3_4 = [
        0.024086272288553298, 0.14935615907137875, 0.1636409987699643, 0.17793268436448548, 0.19060799466217285,
        0.19273901367338866, 0.19452370741369837, 0.1998104097475886, 0.20223929962951073, 0.198855169159512,
        0.20369323744482448, 0.20506770406500857, 0.2068035724061262, 0.20687853303960477, 0.1994076536087631,
        0.1980600680590136, 0.19549138997299564, 0.1956574229752408, 0.19892436010605252, 0.1960383238342662,
        0.19663163223519062, 0.19413349631066903, 0.19613769330478942, 0.19250580430825256, 0.18823192123350746,
        0.18987330430701638, 0.1900430725279835, 0.19170979457969717, 0.18999052787556792, 0.1914805040560624,
        0.18913022393479725, 0.18982512485719882, 0.18548769872474322, 0.18052398353403104, 0.17700067413680656,
        0.17194265031769396, 0.1721783335962553, 0.16815874601416286, 0.16543812310618006, 0.163501941413615
    ]
#
# Type 3 max MRR is at 14 with MRR of 0.20687853303960477
#
bellon_t3_5 = [
    0.024086272288553298, 0.14935615907137875, 0.1636409987699643, 0.17793268436448548, 0.19060799466217285,
    0.19273901367338866, 0.19452370741369837, 0.1998104097475886, 0.20223929962951073, 0.198855169159512,
    0.20369323744482448, 0.20506770406500857, 0.2068035724061262, 0.20687853303960477, 0.19943931558217262,
    0.19733191271920777, 0.1955045240880715, 0.19791123712225886, 0.19892436010605252, 0.19544991547886756,
    0.19663163223519062, 0.19585022875792477, 0.19613769330478942, 0.19250580430825256, 0.1885555458289767,
    0.1898395710995861, 0.18989910930386056, 0.19170979457969717, 0.18999052787556792, 0.1914805040560624,
    0.18911805759662176, 0.18982512485719882, 0.18548769872474322, 0.18052398353403104, 0.17700067413680656,
    0.17194265031769396, 0.1721783335962553, 0.16815874601416286, 0.16549316894269883, 0.1635686738022792,
]
#
# Type 3 max MRR is at 14 with MRR of 0.20687853303960477
#

# bellon_t2 = [0.20926548788012778, 0.2783360389627968, 0.3111033385169024, 0.32130126251700986, 0.3299005733759096,
#              0.3292520637498434, 0.3285375401275418, 0.3318925462701725, 0.31768634570431026, 0.3146043915833309,
#              0.31954401292254647, 0.31526144489797725, 0.3161349103764329, 0.3215898760670963, 0.32047305340246507,
#              0.3198000809273281, 0.33357301152789187, 0.33303938304356795, 0.33330660053101635, 0.33285761502051725,
#              0.33158204553428466, 0.33105758011313696, 0.31547155090637324, 0.3330977381398633, 0.33289760517504163,
#              0.3287100218966402, 0.3274625365879889, 0.32624670945146156, 0.32308319574659466, 0.32094594754162303,
#              0.3207482972898641, 0.32110320537591946, 0.32086682105050046, 0.3201988442402352, 0.3212364559940088,
#              0.3184693036585782, 0.31057660055621594, 0.3067445707959212, 0.28649715632610034, 0.2743999502301123]
#
# bellon_t3 = [0.024086272288553298, 0.14935615907137875, 0.1636409987699643, 0.17793268436448548, 0.19060799466217285,
#              0.19273901367338866, 0.19452370741369837, 0.1998104097475886, 0.20223929962951073, 0.198855169159512,
#              0.20369323744482448, 0.20506770406500857, 0.2068035724061262, 0.20687853303960477, 0.2016931297291907,
#              0.19826774356879612, 0.19770669601240826, 0.19791123712225886, 0.19771076787304281, 0.196766479174072,
#              0.19663163223519062, 0.1942321057805785, 0.19613769330478942, 0.19250580430825256, 0.19017366880632297,
#              0.1898395710995861, 0.19151723228120685, 0.19170979457969717, 0.18999052787556792, 0.1914805040560624,
#              0.18913022393479725, 0.18982512485719882, 0.18548769872474322, 0.18052398353403104, 0.17700067413680656,
#              0.17194265031769396, 0.1721783335962553, 0.16815874601416286, 0.16549316894269883, 0.1635686738022792]

fig = plt.figure()
ax = fig.add_subplot(111)
ax.scatter([15, 14, 17, 24, 14],
           [0.5761094301165004, 0.5738031570537699, 0.33357301152789187, 0.33415753287435684, 0.20687853303960477],
           c="black", marker="o", label="best MRR")
ax.plot(size, bellon_t1_1, c="red", linestyle="-.", label=r'$(r_1^1)$ 15=0.576')
ax.plot(size, bellon_t1_2, c="red", linestyle="-.", label=r'$(r_1^2)$ 14=0.574')
ax.plot(size, bellon_t1_3, c="red", linestyle="-.", label=r'$(r_1^3)$ 15=0.576')
ax.plot(size, bellon_t1_4, c="red", linestyle="-.", label=r'$(r_1^4)$ 14=0.574')
ax.plot(size, bellon_t1_5, c="red", linestyle="-.", label=r'$(r_1^5)$ 14=0.574')
ax.plot(size, bellon_t2_1, c="green", linestyle="--", label=r'$(r_2^1)$ 17=0.334')
ax.plot(size, bellon_t2_2, c="green", linestyle="--", label=r'$(r_2^2)$ 24=0.334')
ax.plot(size, bellon_t2_3, c="green", linestyle="--", label=r'$(r_2^3)$ 24=0.334')
ax.plot(size, bellon_t2_4, c="green", linestyle="--", label=r'$(r_2^4)$ 24=0.334')
ax.plot(size, bellon_t2_5, c="green", linestyle="--", label=r'$(r_2^5)$ 24=0.334')
ax.plot(size, bellon_t3_1, c="blue", linestyle=":", label=r'$(r_3^1)$ 14=0.207')
ax.plot(size, bellon_t3_2, c="blue", linestyle=":", label=r'$(r_3^2)$ 14=0.207')
ax.plot(size, bellon_t3_3, c="blue", linestyle=":", label=r'$(r_3^3)$ 14=0.207')
ax.plot(size, bellon_t3_4, c="blue", linestyle=":", label=r'$(r_3^4)$ 14=0.207')
ax.plot(size, bellon_t3_5, c="blue", linestyle=":", label=r'$(r_3^5)$ 14=0.207')

plt.xticks(list(arange(0, max + 1, 4)))
plt.yticks(arange(0.0, 0.7, 0.1))
ax.yaxis.label.set_size(16)
ax.xaxis.label.set_size(16)
ax.yaxis.set_tick_params(labelsize=14)
ax.xaxis.set_tick_params(labelsize=14)
plt.xlabel("n-gram size")
plt.ylabel("MRR")
plt.legend(loc='lower right', ncol=4, prop={'size': 12})
plt.show()

fig = ax.get_figure()
fig.set_size_inches(10, 7)
fig.savefig('ngram_mrr.pdf', bbox_inches='tight')