-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2022 at 07:26 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dethitracnghiem`
--

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `ExamID` varchar(255) NOT NULL,
  `ExamTitle` varchar(255) DEFAULT NULL,
  `Creator` int(11) NOT NULL,
  `SubjectID` int(11) NOT NULL,
  `NumOfQuiz` int(11) DEFAULT NULL,
  `LimitTime` int(11) DEFAULT NULL,
  `ExamStatus` bit(1) NOT NULL DEFAULT b'0',
  `NumOfDo` int(11) NOT NULL DEFAULT 0,
  `HighestScore` float DEFAULT NULL,
  `LowestScore` float DEFAULT NULL,
  `AvgScore` float DEFAULT NULL,
  `Visible` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ExamID`, `ExamTitle`, `Creator`, `SubjectID`, `NumOfQuiz`, `LimitTime`, `ExamStatus`, `NumOfDo`, `HighestScore`, `LowestScore`, `AvgScore`, `Visible`) VALUES
('1', 'English', 2, 1, 55, 60, b'0', 0, NULL, NULL, NULL, b'1'),
('2', 'English', 2, 1, 10, 1, b'0', 0, NULL, NULL, NULL, b'1'),
('3', 'English', 2, 1, 10, 10, b'0', 0, NULL, NULL, NULL, b'1'),
('4', 'English', 2, 1, 10, 10, b'0', 0, NULL, NULL, NULL, b'1'),
('5', 'English', 2, 1, 10, 10, b'0', 0, NULL, NULL, NULL, b'1'),
('6', 'English', 2, 1, 25, 60, b'0', 0, NULL, NULL, NULL, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `examquestion`
--

CREATE TABLE `examquestion` (
  `ExamID` varchar(255) NOT NULL,
  `Number` int(11) NOT NULL,
  `Question` varchar(255) NOT NULL,
  `Choice1` varchar(255) NOT NULL,
  `Choice2` varchar(255) NOT NULL,
  `Choice3` varchar(255) NOT NULL,
  `Choice4` varchar(255) NOT NULL,
  `Answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `examquestion`
--

INSERT INTO `examquestion` (`ExamID`, `Number`, `Question`, `Choice1`, `Choice2`, `Choice3`, `Choice4`, `Answer`) VALUES
('1', 1, 'Who are all ________ people?', 'A) this', '(B) those', '(C) them', '(D) that', '?'),
('1', 2, 'I do not know ________ people.', 'A) many', '(B) much', '(C) a lot', '(D) few', '?'),
('1', 3, ' We live near ________ the river.', '(A) of', '(B) from', '(C) by', '(D) -', '?'),
('1', 4, ' He says that he must have ________.', '(A) the all from it', '(B) all it', '(C) the all of it', '(D) it all', '?'),
('1', 5, ' John is a good worker: He works very ________.', '(A) hardly', '(B) hard', '(C) good', '(D) many', '?'),
('1', 6, ' Let us ________ a party!', '(A) do', '(B) have', '(C) set', '(D) make', '?'),
('1', 7, 'The radio is much too loud: please turn it ________.', '(A) out', '(B) low', '(C) up', '(D) down', '?'),
('1', 8, '- Would you like some sugar?\r\n- Yes, please, just ________. ', '(A) a little', '(B) little', '(C) a few', '(D) few', '?'),
('1', 9, 'They say he has ________ money.', '(A) a lot of', '(B) plenty', '(C) lots', '(D) many', '?'),
('1', 10, 'How much time do you ________ your homework?', '(A) make at', '(B) bring to', '(C) give for', '(D) spend on', '?'),
('1', 11, 'My father is ________ man.', '(A) an old', '(B) a old', '(C) not young', '(D) not an young', '?'),
('1', 12, 'Millions of cigarettes ________ every year.', '(A) is smoke', '(B) are smoking', '(C) are smoked', '(D) are smoke', '?'),
('1', 13, 'Gold, as well as silver ________ in price, he said.', '(A) have fallen', '(B) has fallen', '(C) fall', '(D) are falling', '?'),
('1', 14, 'They quarreled ________ the choice of a house.', '(A) on', '(B) over', '(C) for', '(D) to', '?'),
('1', 15, '15. Fred was a really silly boy when we were in high-school. I still remember ________ very stupid questions.', '(A) his asking', '(B) him to ask', '(C) asking him', '(D) his being asked', '?'),
('1', 16, '. ________ I like the Rolling Stones.', '(A) No need to say', '(B) Do not need to say', '(C) Needless saying', '(D) Needless to say', '?'),
('1', 17, '- Are you sorry that you did not take pictures?\r\n     - Yes, I regret ________ any.', '(A) not to take', '(B) that I not take', '(C) not taking', '(D) to not taken', '?'),
('1', 18, 'If he had not given me advice, I ________ again.', '(A) would fail', '(B) would be failed', '(C) would not fail', '(D) would have failed', '?'),
('1', 19, 'What ________ ! The rain has not stopped all day.', '(A) a weather', '(B) the weather', '(C) weather', '(D) an weather', '?'),
('1', 20, ' - Which of the two boys is a boy scout?\r\n     - ________ of them is.', '(A) All', '(B) None', '(C) Neither', '(D) Both', '?'),
('1', 21, 'Every morning I wash my face and clean my teeth by _____.', 'A. I', 'B. mine', 'C. my', 'D. myself', '?'),
('1', 22, 'Nam was absent from class yesterday _____ he felt sick.', 'A. so', 'B. because', 'C. although', 'D. but', '?'),
('1', 23, 'Tom has a computer, _____ he doesn’t use it.', 'A. or', 'B. as', 'C. because', 'D. but', '?'),
('1', 24, 'Water is _______ than tequila.', 'A. healthier', 'B. more healthier', 'C. most healthy', 'D. healthiest', '?'),
('1', 25, 'I was ill yesterday but I am ________ today.', 'A. better', 'B. gooder', 'C. weller', 'D. best', '?'),
('1', 26, 'She would go to the Job Centre if she ________ a job.', 'A. had wanted', 'B. will want', 'C. wanted', 'D. wants', '?'),
('1', 27, 'She could not eat anything at the meat restaurant _____she is a vegetarian.', 'A. So', 'B. because', 'C. because of', 'D. although', '?'),
('1', 28, 'Do you know anyone ______ speaks Japanese?', 'A. which', 'B. whom', 'C. whose', 'D. that', '?'),
('1', 29, 'The guy ______ was very friendly.', 'A. that I met', 'B. which I met', 'C. who met', 'D. whose met', '?'),
('1', 30, 'I talked to _______ grandmother for three hours last night.', 'A. he', 'B. him', 'C. his', 'D. himself', '?'),
('1', 31, '________ grade are you in?', 'A. When', 'B. Which', 'C. Where', 'D. How', '?'),
('1', 32, 'Hoa lives ______Ha Noi, ______ 12 Tran Hung Dao Street.', 'A. on – at', 'B. in – on', 'C. in – at', 'D. on – in', '?'),
('1', 33, 'Hung _______ from Hanoi but he is staying with his relatives in Ho Chi Minh City at the moment.', 'A. Will come', 'B. Came', 'C. Come', 'D. Comes', '?'),
('1', 34, '– \\\"Hi, Phong. Nice to meet you again!\\\"- \\\"Hi, Kha. Nice to meet you, ______.\\\"', 'A. too', 'B. also', 'C. so', 'D. then', '?'),
('1', 35, '__________ is from your house to the post office?', 'A. How much', 'B. How many', 'C. How often', 'D. How far', '?'),
('1', 36, 'How __________ rice do you want?', 'A. much', 'B. many', 'C. a lot of', 'D. lots of', '?'),
('1', 37, '\\\"What is your ___________, Hoa?\\\" \\\"- It is Pham. My middle name is Thi.\\\"', 'A. middle', 'B. family', 'C. full', 'D. age', ''),
('1', 38, ' Would you like _______ a movie?', 'A. see', 'B. seeing', 'C. to seeing', 'D. to see', ''),
('1', 39, 'Hoa has lots of friends in Hue but she ________ any friends in Ha Noi.', 'A. not have', 'B. do not have', 'C. does not have', 'D. is not have', ''),
('1', 40, 'Her old school is ________ than her new school.', 'A. big', 'B. the biggest', 'C. the bigger', 'D. bigger', ''),
('1', 41, 'What are you..................? -I am playing soccer.', 'A. Do', 'B. to do', 'C. doing', 'D. does', ''),
('1', 42, 'Peter........to Linda about his vacation last week.', 'A. talks', 'B. talked', 'C. talking', 'D. to talk', ''),
('1', 43, '...........is the matter with you? – I have a toothache.', 'A. What', 'B. How', 'C. When', 'D. Why', ''),
('1', 44, ' What does he look..............................? – He is fat and short.', 'A. Like', 'B. at', 'C. for', 'D. after', ''),
('1', 45, 'Lan is in my class. She is my _______.', 'A. classroom', 'B. classmate', 'C. class', 'D. neighbor', ''),
('1', 46, 'Choose the sentence (A, B, C or D) which has a similar meaning to the original sentence.\\r\\nin / city / busy / Life / is / the / always.', 'A. City is always busy in the life', 'B. Life always busy in is the city', 'C. Life is busy always in the city', 'D. Life is always busy in the city', ''),
('1', 47, 'often/ before/ go/ I/ home/ do not/ seven', 'A. I don’t before often go home seven', 'B. I don’t often go home before seven', 'C. I don’t before seven often go home', 'D. I don’t often before go home seven', ''),
('1', 48, '', '', '', '', '', ''),
('1', 49, ' on/ of/ birthday/ September// is/ second/ her/ the', 'A. September is her birthday on the second of', 'B. Her birthday on is the second of September', 'C. Her birthday is on the second of September', 'D. September of is her birthday on the second', ''),
('1', 50, 'after / What / you / do / usually do / school ?', 'A. What you do after school usually do ?', 'B. What you usually do after do school?', 'C. What do you usually do after school?', 'D. What you do usually do after school?', ''),
('1', 51, 'Louise was listening to the radio while she .................. the house.', 'A. is cleaning', 'B. was cleaning', 'C. had cleaned', 'D. will dean', ''),
('1', 52, 'Television is a prime source of home entertainment; ................, it is a cheap babysitter.', 'A. furthermore', 'B. therefore', 'C. accordingly', 'D. on the contrary', ''),
('1', 53, 'We always ...................... a present to Elsa on her birthday.', 'A. are taking', 'B. are taken', 'C. take', 'D. taking', ''),
('1', 54, 'I’m sorry, I can’t remember ........ her last week.', 'A. are taking', 'B. are taken', 'C. take', 'D. taking', ''),
('1', 55, 'Some students are nervous of computers or anything high-tech ................. others enjoy new challenges and regard learning how to use computers as a kind of game.', 'A. and', 'B. but', 'C. while', 'D. however', ''),
('2', 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'A. were hanging', 'B. were hung', 'C. were hanged', 'D. had been hung', 'D'),
('2', 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'A. bar', 'B. bunch', 'C. pack', 'D. packet', 'B'),
('2', 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'A. fallen', 'B. dropped', 'C. given', 'D. put', 'A'),
('2', 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'A. the one of you', 'B. one of yours', 'C. one pen of you', 'D. the one of your pen', 'B'),
('2', 5, 'There are only a few minutes left, and the students is writing ________________.', 'A. with a pressure', 'B. under the pressure', 'C. to pressure', 'D. under pressure', 'D'),
('2', 6, 'A little farther down the street _______________.', 'A. is the inn I used to stay at', 'B. there is an inn where I used to stay in', 'C. the inn is the place where I used to stay', 'D. is there an inn in which I used to stay', 'A'),
('2', 7, 'Remember to appreciate what your friends do for you. You shouldn’t take them ________.', 'A. as a rule', 'B. as usual', 'C. out of habit', 'D. for granted', 'D'),
('2', 8, '“I wonder if you could help me?” - “______________”', 'A. No, what is it?', 'B. Really? How nice.', 'C. Don’t mention it', 'D. I’ll do my best. What’s the problem?', 'D'),
('2', 9, ' “ Don’t stay anywhere near the railway station at night. It’s dangerous.” - “_____________.”', 'A. Ok. That will do.', 'B. They won’t like it, I bet.', 'C. I definitely won’t. Thank you.', 'D. Where on Earth have you been?', 'C'),
('2', 10, '________every major judo title, Mark retired from international competition.', 'A. When he won', 'B. Having won', 'C. Winning', 'D. On winning', 'B'),
('3', 1, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'A. shade', 'B. shadow', 'C. benefit', 'D. hue', 'B'),
('3', 2, 'She was ________ out of 115 applicants for the position of managing Director.', 'A. short-changed', 'B. short-listed', 'C. shorted-sighted', 'D. short-handed', 'B'),
('3', 3, 'It seems that the world record for this event is almost impossible to ________', 'A. get', 'B. beat', 'C. take', 'D. achieve', 'B'),
('3', 4, 'The smell was so bad that it completely ________ us off our food.', 'A. set', 'B. took', 'C. got', 'D. put', 'D'),
('3', 5, 'He has been waiting for this letter for days, and at ________ it has come.', 'A. last', 'B. the end', 'C. present', 'D. the moment', 'A'),
('3', 6, 'It is ________opportunity to see African wildlife in its natural environment.', 'A. an unique', 'B. a unique', 'C. the unique', 'D. unique', 'C'),
('3', 7, 'Peter and Thomas are talking about their mission. - Peter: “Is it important?” - Thomas: “________”', 'A. Not on your life!', 'B. It’s a matter of life and death!', 'C. No worry, that’s nothing', 'D. It’s ridiculous.', 'B'),
('3', 8, 'Ann is asking Mathew’s opinion after biology class. - Ann: “Does the global warming worry you?” - Mathew: “________”', 'A. What a shame!', 'B. Oh, it’s hotter and hotter.', 'C. I can’t bear to think about it.', 'D. I don’t like hot weather.', 'C'),
('3', 9, 'I’ve never really enjoyed going to the ballet or the opera; they’re not really my ________.', 'A. piece of cake', 'B. sweets and candy', 'C. biscuit', 'D. cup of tea', 'D'),
('3', 10, 'Paul is a very _______ character, he is never relaxed with strangers.', 'A. self-conscious', 'B. self-satisfied', 'C. self-directed', 'D. self-confident', 'A'),
('4', 1, 'The authorities _______ actions to stop illegal purchase of wild animals and their associated products effectively. However, they didn’t do so.', 'A. must have taken', 'B. had to take', 'C. needed have taken', 'D. should have taken', 'D'),
('4', 2, 'Although he is my friend, I find it hard to _______ his selfishness', 'A. put up with', 'B. catch up with', 'C. keep up with', 'D. come down with', 'A'),
('4', 3, 'We expected him at eight, but he finally _______at midnight.', 'A. turned up', 'B. came off', 'C. came to', 'D. turned in', 'A'),
('4', 4, 'The 1st week of classes at university is a little ______ because so many students get lost, change classes or go to the wrong place.', 'A. uncontrolled', 'B. arranged', 'C. chaotic', 'D. notorious', 'C'),
('4', 5, 'You can ask Matin anything about history. He actually has quite a good _____ for facts.', 'A. head ', 'B. understanding', 'C. knowledge', 'D. ability', 'A'),
('4', 6, 'His jokes seemed to ______ a treat with his audience, if their laughter was any indication.', 'A. go along', 'B. go by', 'C. go off', 'D. go down', 'C'),
('4', 7, 'It was such a sad film that we all were reduced_______ tears at the end.', 'A. with', 'B. onto', 'C. to', 'D. into', 'C'),
('4', 8, 'The baby can`t even sit up yet, ______ stand and walk!', 'A. but for', 'B. let alone', 'C. all but', 'D. rather than', 'B'),
('4', 9, 'I don’t think that everyone likes the way he makes fun, ______?', 'A. don`t I', 'B. do I', 'C. don’t they', 'D. do they', 'D'),
('4', 10, 'Unfortunately, the injury may keep him out of football _______. He may never play again.', 'A. for good', 'B. now and then', 'C. once in a while', 'D. every so often', 'A'),
('5', 1, 'I used to ______ reading comics, but now I`ve grown out of it.', 'A. take a fancy to', 'B. keep an eye on', 'C. get a kick out of', 'D. kick up a fuss about', 'C'),
('5', 2, 'Little Deon: “This herb smells horrible, mommy!” - Mommy: “______, it will do you a power of good.”', 'A. Come what may', 'B. By the by', 'C. What is more', 'D. Be that as it may', 'D'),
('5', 3, 'In an ________ to diffuse the tension, I suggest that we go to see a movie.', 'A. attempt', 'B. improvement', 'C. determination', 'D. capability', 'A'),
('5', 4, 'We are surprised to hear that Rose and Jack have ________. They seemed very happy together when I saw them last in Hanoi.', 'A. ended up', 'B. been separating', 'C. split up', 'D. finished up', 'C'),
('5', 5, 'The serious issue ________ question made everyone in the meeting overstressed', 'A. from', 'B. at', 'C. on', 'D. in', 'D'),
('5', 6, 'The profit has now ________ towards the point where it nearly doubled.', 'A. arrived', 'B. approached', 'C. advanced', 'D. reached', 'C'),
('5', 7, 'No matter how angry my mother was, she would never ________ to violence.', 'A. utilize', 'B. resolve', 'C. resort', 'D. resource', 'C'),
('5', 8, 'Whenever the boss does something, he should ______ for the sake of the company', 'A. go down well with', 'B. turn over a new leaf', 'C. weigh up the pros and cons', 'D. get through', 'C'),
('5', 9, 'Hey, have you watched the extensive ________ of sporting events on Star Channel this afternoon?', 'A. broadcast', 'B. network', 'C. coverage ', 'D. vision', 'C'),
('5', 10, '\"How beautiful you are!\" - \"________\"', 'A. Say it again. I like to hear your words.', 'B. Many thanks! That`s a nice to hear', 'C. I think so. I am proud of myself', 'D. Yes, don`t you think?', 'B'),
('6', 1, 'Dick and Joan greeted ________.', '(A) himself', '(B) herself', '(C) themselves', '(D) each other', '?'),
('6', 2, 'Come tomorrow and ________ your books with you.', '(A) take', '(B) bring', '(C) fetch', '(D) hold', '?'),
('6', 3, 'It’s very dark here: please switch the light ________.', '(A) off', '(B) up', '(C) up', '(D) in', '?'),
('6', 4, ' Is the day sunny? No, ________ is dark.', '(A) they', '(B) its', '(C) it`s', '(D) it', '?'),
('6', 5, 'Betty is my friend. She is a friend of ________.', '(A) me', '(B) my', '(C) mine', '(D) I', '?'),
('6', 6, ' When do you go ________ bed?', '(A) to', '(B) to the', '(C) in', '(D) in the', '?'),
('6', 7, 'He is ________ a jacket.', '(A) wearing', '(B) carrying', '(C) having', '(D) holding', '?'),
('6', 8, ' Smith went abroad last year. ________ abroad before.', '(A) He had never been', '(B) He had been never', '(C) He never went', '(D) He went never', '?'),
('6', 9, 'Would you mind ________ the door?', '(A) open', '(B) to open', '(C) opening', '(D) to opening', '?'),
('6', 10, '________ your glasses on the table?', '(A) Is', '(B) Are', '(C) These', '(D) This', '?'),
('6', 11, '– A: ________ you? – B: Yes, please get my luggage from the car.', '(A) Do I help', '(B) Have I help', '(C) Can I help', '(D) Am I helping', '?'),
('6', 12, ' ________ form of transport is as popular as the private car.', '(A) No other', '(B) No others', '(C) None other', '(D) Nothing other', '?'),
('6', 13, 'Yesterday we did nothing but ________.', '(A) talking', '(B) talked', '(C) talk', '(D) had talked', '?'),
('6', 14, 'We haven’t heard ________ the result was.', '(A) what', '(B) which', '(C) that', '(D) when', '?'),
('6', 15, 'This is the second time that you have been here, ________?', '(A) is it', '(B) haven’t you', '(C) don’t you', '(D) isn’t it', '?'),
('6', 16, 'They gave ________ looking for her when it grew dark.', '(A) up', '(B) in', '(C) off', '(D) out', '?'),
('6', 17, 'This year the farmers were just able to gather in the ________ before the fine weather came to an end.', '(A) collection', '(B) seed', '(C) plant', '(D) harvest', '?'),
('6', 18, 'There are many ________ on television where a team of people have to answer questions.', '(A) queries', '(B) riddles', '(C) quizzes', '(D) inquiries', '?'),
('6', 19, ' ________ you angry with him?', '(A) Do', '(B) Why', '(C) Are', '(D) Have', '?'),
('6', 20, ' – You’ve been sick quite a few times since you arrived. – I just can’t ________ to the cold weather.', '(A) have used', '(B) get used', '(C) be used', '(D) used', '?'),
('6', 21, ' I`m very tired - I ________ all morning.', 'A. work', '(B) was worked', '(C) am working', '(D) have been working', '?'),
('6', 22, 'Give me the message to ________ is at the desk!', '(A) that', '(B) what', '(C) whomever', '(D) whoever', '?'),
('6', 23, 'Your mother and I are looking forward ________ you.', '(A) of seeing', '(B) for seeing', '(C) to see', '(D) to seeing', '?'),
('6', 24, 'He ________ for a job for some weeks but he has not found one.', '(A) is looking', '(B) looks', '(C) has looked', '(D) has been looking', '?'),
('6', 25, 'Would you mind ________ the window?', '(A) open', '(B) to open', '(C) opening', '(D) not to open', '?');

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `ResultID` int(11) NOT NULL,
  `ExamID` varchar(255) NOT NULL,
  `Examinee` varchar(255) NOT NULL,
  `Score` float NOT NULL,
  `Date` date NOT NULL,
  `CorrectQuiz` int(11) NOT NULL,
  `WrongQuiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`ResultID`, `ExamID`, `Examinee`, `Score`, `Date`, `CorrectQuiz`, `WrongQuiz`) VALUES
(1, '2', 'lyquocan171@gmail.com', 2, '2022-12-15', 2, 8);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `SubjectID` int(11) NOT NULL,
  `SubjectName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SubjectID`, `SubjectName`) VALUES
(1, 'English'),
(2, 'Math'),
(3, 'Physic');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Fullname` varchar(255) NOT NULL,
  `Birth` date NOT NULL,
  `Gender` bit(1) NOT NULL,
  `LogStatus` bit(1) NOT NULL DEFAULT b'0',
  `BlockLogin` bit(1) NOT NULL DEFAULT b'0',
  `BlockAddExam` bit(1) NOT NULL DEFAULT b'0',
  `BlockTakeExam` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `Password`, `Fullname`, `Birth`, `Gender`, `LogStatus`, `BlockLogin`, `BlockAddExam`, `BlockTakeExam`) VALUES
(1, '1@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', 'KingNguyen', '0000-00-00', b'0', b'0', b'0', b'0', b'0'),
(2, 'KingError@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', 'VinhNguyen', '0000-00-00', b'1', b'0', b'1', b'0', b'0'),
(3, 'hc@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Hải Cẩu lười', '2022-11-09', b'1', b'0', b'0', b'0', b'0'),
(4, 'hml@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', 'Hàng ML', '2022-12-01', b'1', b'0', b'0', b'0', b'0'),
(5, 'hcc@gmail.com', '08cc7822bf21ce087c85f210fb16776a', 'dawdjhwad', '2022-12-03', b'1', b'0', b'0', b'0', b'0'),
(6, 'sealthe3rd001@gmail.com', '08cc7822bf21ce087c85f210fb16776a', 'dawdawfaf', '2022-12-01', b'0', b'0', b'0', b'0', b'0'),
(7, 'lyquocan171@gmail.com', '4ca83fb4104f5288c6c5c003a896603c', 'an', '2022-12-01', b'1', b'0', b'0', b'0', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`ExamID`),
  ADD KEY `Creator` (`Creator`),
  ADD KEY `SubjectID` (`SubjectID`);

--
-- Indexes for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD PRIMARY KEY (`ExamID`,`Number`),
  ADD KEY `ExamID` (`ExamID`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`ResultID`),
  ADD KEY `ExamID` (`ExamID`),
  ADD KEY `Examinee` (`Examinee`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`SubjectID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `ResultID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `SubjectID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`),
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`Creator`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD CONSTRAINT `examquestion_ibfk_1` FOREIGN KEY (`ExamID`) REFERENCES `exam` (`ExamID`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`ExamID`) REFERENCES `exam` (`ExamID`),
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`Examinee`) REFERENCES `user` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
