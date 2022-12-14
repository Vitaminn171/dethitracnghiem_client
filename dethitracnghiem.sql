-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Dec 14, 2022 at 07:35 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

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
  `AvgScore` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ExamID`, `ExamTitle`, `Creator`, `SubjectID`, `NumOfQuiz`, `LimitTime`, `ExamStatus`, `NumOfDo`, `HighestScore`, `LowestScore`, `AvgScore`) VALUES
('2', 'English', 2, 1, 10, 1, b'0', 0, 10, 0, 5),
('3', 'English', 2, 1, 10, 10, b'0', 0, 10, 0, 5),
('4', 'English', 2, 1, 10, 10, b'0', 0, 10, 0, 5),
('5', 'English', 2, 1, 10, 10, b'0', 0, 10, 0, 5);

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
('5', 10, '\"How beautiful you are!\" - \"________\"', 'A. Say it again. I like to hear your words.', 'B. Many thanks! That`s a nice to hear', 'C. I think so. I am proud of myself', 'D. Yes, don`t you think?', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `ResultID` int(11) NOT NULL,
  `ExamID` varchar(255) NOT NULL,
  `Examinee` varchar(30) NOT NULL,
  `Score` float NOT NULL,
  `Date` date NOT NULL,
  `CorrectQuiz` int(11) NOT NULL,
  `WrongQuiz` int(11) NOT NULL,
  `NumOfTime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`ResultID`, `ExamID`, `Examinee`, `Score`, `Date`, `CorrectQuiz`, `WrongQuiz`, `NumOfTime`) VALUES
(1, '2', 'lyquocan171@gmail.com', 2, '2022-12-15', 2, 8, 1);

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
  `BlockStatus` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `Password`, `Fullname`, `Birth`, `Gender`, `LogStatus`, `BlockStatus`) VALUES
(1, '1@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', 'KingNguyen', '0000-00-00', b'0', b'0', b'0'),
(2, 'KingError@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', 'VinhNguyen', '0000-00-00', b'1', b'1', b'1'),
(3, 'hc@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Hải Cẩu lười', '2022-11-09', b'1', b'1', b'0'),
(4, 'hml@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', 'Hàng ML', '2022-12-01', b'1', b'0', b'0'),
(5, 'hcc@gmail.com', '08cc7822bf21ce087c85f210fb16776a', 'dawdjhwad', '2022-12-03', b'1', b'0', b'0'),
(6, 'sealthe3rd001@gmail.com', '08cc7822bf21ce087c85f210fb16776a', 'dawdawfaf', '2022-12-01', b'0', b'1', b'0'),
(7, 'lyquocan171@gmail.com', '4ca83fb4104f5288c6c5c003a896603c', 'an', '2022-12-01', b'1', b'1', b'0');

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
